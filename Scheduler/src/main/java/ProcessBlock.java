public class ProcessBlock extends Process {

    public ProcessBlock(String name, Scheduler scheduler) {
        super(name, scheduler);
    }

    @Override
    public void Work() {
        try {
            System.out.println("working...");
            sleep(1000);
            use(Impresora.impresora);
            finish();
        } catch (InterruptedException e) {
            System.out.println("no se pudo imprimir");
        }
    }

    @Override
    public void use(Resource resource) throws InterruptedException {
        System.out.println("asking for the resource");
        this.state = states.BLOCKED;
        scheduler.Block(this, resource);
    }

    @Override
    public void finish() {
        System.out.println("done");
        this.state = states.DONE;
        this.interrupt();
    }
}
