import java.util.concurrent.Semaphore;
import java.lang.Thread;

public class Process extends Thread {
    protected enum states {
        READY,
        IN_PROGRESS,
        BLOCKED,
        SUSPENDED, // no implementado
        DONE
    }

    protected static Semaphore mutex = new Semaphore(1, true);

    // nombre del proceso
    protected String processName;

    // estado del proceso
    protected states state;

    // instancia de scheduler para comunicarse
    protected Scheduler scheduler;

    // simula la duracion del programa
    protected int programCounter;


    public String getProcessName() {
        return processName;
    }

    public Process(String name, Scheduler scheduler){
        this.processName = name;
        this.scheduler = scheduler;
    }


    public String getEstado() {
        return this.state.toString();
    }

    public void setEstadoIN_PROGRESS() {
        this.state = states.IN_PROGRESS;
    }

    public void setEstadoREADY() {
        this.state = states.READY;
    }

    @Override
    public void run() {
        // inicializo duracion del proceso en 20 segundos
        programCounter = 20000;

        System.out.println("initializing process thread...");
        // agrego proceso a cola de listos
        scheduler.Listo(this);

        while (this.state != states.DONE) {
            while (this.state != states.IN_PROGRESS) {
                try {
                    // mientras no le toca procesador espera
                    synchronized (Syncro.syncro) {
                        Syncro.syncro.wait();
                    }
                    // si interrumpen el wait y el estado es IN_PROGRESS es porque le dieron CPU
                    // de lo contrario se vuelve a bloquear
                } catch (InterruptedException ignored) {
                }
            }
            try {
                Work();
            // atrapo interrupcion provocada por el timeout
            } catch (InterruptedException e) {
                System.out.println(getProcessName() + " timeout");
                //scheduler.procesoEnEjecucion.remove(this); // pierde CPU
                scheduler.Listo(this); // vuelve a estar listo
                programCounter -= 5000; // simula que ejecuta por 5 segundos en cada vuelta
                mutex.release(); // libero mutua exclusion
            }
        }
    }

    public void Work() throws InterruptedException {
        // mutua exclusion preventiva, ningun proceso puede estar ejecutandose a la vez
        // si algun hilo se escapa del wait queda bloqueado hasta que el anterior termine
        mutex.acquireUninterruptibly();

        // simula trabajo
        System.out.println(this.getProcessName() + " working");
        sleep(programCounter);

        // finaliza el proceso
        finish();
    }

    public void use(Resource resource) throws InterruptedException {
    }

    public void finish() {
        // reset de program counter por si se quiere ejecutar de nuevo
        // (aunque ya se hace en la inicializacion del proceso)
        programCounter = 20000;

        // trabajo finalizado
        System.out.println(getProcessName() + " done");
        this.state = states.DONE;

        // libero mutua exclusion
        mutex.release();
    }
}
