import java.util.concurrent.Semaphore;

public class ProcessBlock extends Process {

    protected static Semaphore mutex = new Semaphore(1, true);

    private Resource resource;

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public ProcessBlock(String name, Scheduler scheduler) {
        super(name, scheduler);
    }

    @Override
    public void run() {
        programCounter = 10000;
        System.out.println("initializing process thread...");
        scheduler.Listo(this);
        while (this.state != states.DONE) {
            while (this.state != states.IN_PROGRESS) {
                try {
                    synchronized (Syncro.syncro) {
                        Syncro.syncro.wait();
                    }
                } catch (InterruptedException ignored) {
                }
            }
            try {
                // el proceso usa el recurso si el scheduler se lo dio
                if (resource != null) {
                    resource.usar();
                    finish();
                }
                else
                    Work();
            } catch (InterruptedException e) {
                //this.state = states.READY;
                programCounter -= 5000; // simula que ejecuta por 5 segundos en cada vuelta
                mutex.release();
            }
        }
    }

    @Override
    public void Work() throws InterruptedException {
        System.out.println(getProcessName() + " asking for the resource");

        // proceso va a lista de bloqueados
        scheduler.procesosBloqueados.add(this);
        // proceso se quita de lista de listos
        scheduler.procesosListos.remove(this);
        this.state = states.BLOCKED;
        mutex.acquireUninterruptibly();
        // proceso pide un recurso
        use(Impresora.impresora);
    }

    @Override
    public void use(Resource resource) throws InterruptedException {
        // pide recurso al scheduler
        scheduler.Block(this, resource);
    }

    @Override
    public void finish() {
        programCounter = 10000;
        // "entrega" el recurso
        this.resource = null;
        // finaliza el trabajo
        System.out.println(getProcessName() + " done");
        this.state = states.DONE;
        mutex.release();
    }
}
