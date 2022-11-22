public class Main {
    public static void main(String[] args) {

        // creo scheduler
        Scheduler scheduler = new Scheduler();

        // creo trabajos (que no piden por I/O, por lo que no se bloquean)
        Process process1 = new Process("Process 1", scheduler);
        Process process2 = new Process("Process 2", scheduler);
        Process process3 = new Process("Process 3", scheduler);
        Process process4 = new Process("Process 4", scheduler);
        Process process5 = new Process("Process 5", scheduler);

        // creo trabajos (que sí piden por I/O, por lo que se bloquean)
        Process processBlock1 = new ProcessBlock("Process Block 1", scheduler);
        Process processBlock2 = new ProcessBlock("Process Block 2", scheduler);
        Process processBlock3 = new ProcessBlock("Process Block 3", scheduler);
        Process processBlock4 = new ProcessBlock("Process Block 4", scheduler);
        Process processBlock5 = new ProcessBlock("Process Block 5", scheduler);

        // inicializo scheduler
        scheduler.start();

        // inicializo todos los procesos
        processBlock1.start();
        processBlock2.start();
        processBlock3.start();
        processBlock4.start();
        processBlock5.start();
        process1.start();
        process2.start();
        process3.start();
        process4.start();
        process5.start();
    }
}
