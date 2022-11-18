import static java.lang.Thread.sleep;

public class Impresora extends Resource {

    public static final Impresora impresora = new Impresora();

    @Override
    public void usar() throws InterruptedException {
        enUso = true;
        System.out.println("imprimiendo...");
        sleep(3000);
        System.out.println("impreso");
        soltar();
    }

    @Override
    public void soltar() throws InterruptedException {
        enUso = false;
        synchronized (impresora) {
            notify();
        }
    }
}
