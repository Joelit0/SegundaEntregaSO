public class Main {
    public static void main(String[] args) {
        final int PRODUCTOR = 3;
        final int CONSUMIDOR = 10;

        Buffer buffer = new Buffer();

        for (int i = 0; i < PRODUCTOR; i++) {
            new Productor("Productor " + i, buffer).start();
        }

        for (int i = 0; i < CONSUMIDOR; i++) {
            new Consumidor("Consumidor " + i, buffer).start();
        }
    }
}
