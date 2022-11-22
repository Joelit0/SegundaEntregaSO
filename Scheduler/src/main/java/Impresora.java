import static java.lang.Thread.sleep;
import java.util.concurrent.Semaphore;

// hereda de recurso
public class Impresora extends Resource {

    // instancia de impresora
    public static final Impresora impresora = new Impresora();

    private Semaphore mutex = new Semaphore(1);

    @Override
    public void usar() throws InterruptedException {
        // bool que representa si un proceso esta usando la impresora
        enUso = true;
        // simulo trabajo
        sleep(3000);
        System.out.println("impreso");
        soltar();
    }

    @Override
    public void soltar() throws InterruptedException {
        enUso = false;
    }

}
