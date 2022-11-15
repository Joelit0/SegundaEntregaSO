import java.util.concurrent.Semaphore;

public class Buffer {
    private String[] lista = new String[20];

    private int p_a_e = 0;
    private int p_a_i = 0;
    private int cantidad = 0;

    private Semaphore lleno = new Semaphore(20);
    private Semaphore vacio = new Semaphore(0);
    private Semaphore mutex = new Semaphore(1);

    public void insertar_al_final(String nombreProductor, String dato) {

        try {
            lleno.acquire();
            mutex.acquire();

            System.out.println("Productor " + nombreProductor + " produjo " + dato);
            lista[p_a_i] = dato;
            p_a_i = (p_a_i + 1) % 20;
            cantidad++;

            mutex.release();

            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            vacio.release();
        }
    }

    public String extraer_primero(String nombreConsumidor) {
        String dato;

        try {
            vacio.acquire();
            mutex.acquire();

            dato = lista[p_a_e];
            lista[p_a_e] = null;
            p_a_e = (p_a_e + 1) % 20;
            cantidad--;

            System.out.println("Consumidor " + nombreConsumidor + " consumio " + dato);

            mutex.release();

            Thread.sleep(1000);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lleno.release();
        }

        return dato;
    }
}
