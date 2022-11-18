import java.util.concurrent.Semaphore;
import java.lang.Thread;

public class Process extends Thread {
    protected enum states {
        READY,
        IN_PROGRESS,
        BLOCKED,
        SUSPENDED,
        DONE
    }

    private Semaphore mutex = new Semaphore(1);
    protected String processName;
    protected states state;
    protected Scheduler scheduler;

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
        programCounter = 20000;
        System.out.println("initializing process thread...");
        this.state = states.READY;
        scheduler.Listo(this);
        while (this.state != states.DONE) {
            while (this.state != states.IN_PROGRESS) {
                try {
                    synchronized (Syncro.syncro) {
                        Syncro.syncro.wait();
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            try {
                Work();
            } catch (InterruptedException e) {
                programCounter -= 5000; // simula que ejecuta por 5 segundos en cada vuelta
            }
        }
    }

    public void Work() throws InterruptedException {
        //mutex.acquire();
        System.out.println(this.getProcessName() + " working");
        sleep(programCounter);
        finish();
    }

    public void use(Resource resource) throws InterruptedException {
    }

    public void finish() {
        programCounter = 20000;
        System.out.println(getProcessName() + " done");
        this.state = states.DONE;
        //mutex.release();
    }
}
