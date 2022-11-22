public class Consumidor extends Thread {
    private Buffer buffer;
    private String name;

    public Consumidor(String name, Buffer buffer) {
        this.buffer = buffer;
        this.name = name;
    }

    @Override
    public void run() {
        while(true) {
            buffer.extraer(this.name);
        }
    }
}
