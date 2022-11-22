import java.util.Queue;
import java.util.concurrent.Semaphore;

public class Scheduler extends Thread {

    private Semaphore mutex = new Semaphore(1);
    public Queue<Process> procesosListos = new Lista<>();

    // no es una cola porque pueden ser procesos esperando por
    // diferentes recursos, por lo que no se sabe cual saldra primero
    public Lista<Process> procesosBloqueados = new Lista<>();

    public Queue<Process> procesosSuspendidoListo = new Lista<>();
    public Queue<Process> procesosSuspendidoBloqueado = new Lista<>();

    public Queue<Process> procesoEnEjecucion = new Lista<>();

    public Scheduler(){
    }

    public void run() {
        try {
            Scheduling();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void Scheduling() throws InterruptedException {
        while (true) {
            // mientras no existan procesos espero
            while (procesosListos.size() == 0) {}

            // ejecuto proceso
            EnEjecucion(procesosListos.peek());
            // espero a que efectivamente el proceso quede añadido a la lista
            while (procesoEnEjecucion.size() == 0) {}

            String estadoInicial = "IN_PROGRESS";
            // quantum de tiempo para timeout
            int timeout = 2000000000;
            // mientras el proceso no haya cambiado el estado
            // y no se le haya acabado el tiempo de procesador (timeout) ejecuta
            while (timeout != 0 && (procesoEnEjecucion.peek().getEstado() == estadoInicial)) {
                timeout--;
            }

            estadoInicial = procesoEnEjecucion.peek().getEstado();
            // proceso no terminó
            if (estadoInicial == "IN_PROGRESS") {
                System.out.println(procesoEnEjecucion.peek().getProcessName()  + " has not ended, RR continues");
                procesoEnEjecucion.peek().interrupt();
                //Listo(procesoEnEjecucion.peek());
            }
            // proceso terminó
            else if (estadoInicial == "DONE") {
                System.out.println(procesoEnEjecucion.peek().getProcessName() + " has ended");
            }
            // proceso se bloqueó por recurso
            else if (estadoInicial == "BLOCKED") {
                System.out.println(procesoEnEjecucion.peek().getProcessName() + " has been blocked, RR continues");
            }
            // proceso fue suspendido
            else if (estadoInicial == "SUSPENDED") {
                System.out.println("process has been suspended, RR continues");
            }
            // en cualquier caso pierde procesador
            procesoEnEjecucion.remove(procesoEnEjecucion.peek());
        }
    }

    public void Listo(Process proceso) {
        // añado proceso a cola de listos
        System.out.println("process " + proceso.getProcessName() + " READY");
        proceso.setEstadoREADY();
        procesosListos.add(proceso);
    }

    public synchronized void EnEjecucion(Process proceso) throws InterruptedException {

        // le doy CPU a proceso
        procesoEnEjecucion.add(proceso);
        // lo saco de cola de listos
        procesosListos.remove(proceso);
        System.out.println("process " + proceso.getProcessName() + " IN PROGRESS");

        proceso.setEstadoIN_PROGRESS();
        // despierto a todos los procesos, pero
        // solo ejecutara el que tenga el estado IN_PROGRESS
        // el resto volvera a esperar
        synchronized (Syncro.syncro) {
            Syncro.syncro.notifyAll();
        }

        // simulo trabajo (dentro del proceso)

    }

    // no implementado
    public void Suspender(Process proceso) {
        // si el proceso suspendido estaba listo, va a suspendido-listo
        System.out.println(proceso.getProcessName() + "SUSPENDED");
        if (procesosListos.contains(proceso)) {
            procesosSuspendidoListo.add(proceso);
            procesosListos.remove(proceso);
        }
        // si el proceso suspendido estaba bloqueado, va a suspendido-bloqueado
        if (procesosBloqueados.contains(proceso)) {
            procesosSuspendidoBloqueado.add(proceso);
            procesosBloqueados.remove(proceso);
        }
    }

    public void Block(ProcessBlock process, Resource resource) throws InterruptedException {
        System.out.println("resourse given to " + process.getProcessName());
        // entrego recurso
        process.setResource(resource);
        // elimino de procesos bloqueados porque ya tiene recurso
        procesosBloqueados.remove(process);
        // agrego a cola de listos
        Listo(process);
    }
}
