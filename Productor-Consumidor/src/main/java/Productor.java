import java.util.Random;

public class Productor extends Thread {
    private Buffer buffer;
    private String name;
    final char[] letras = "QWERTYUIOPASDFGHJKLZXCVBNM".toCharArray();

    public Productor(String name, Buffer buffer) {
        this.buffer = buffer;
        this.name = name;
    }

    @Override
    public void run() {
        Random r = new Random();

        while(true) {
            buffer.insertar(
                    this.name,
                    String.valueOf(letras[r.nextInt(26)])
            );
        }
    }
}
