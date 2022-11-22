import java.util.LinkedList;

// LinkedList con sincronizacion
public class Lista<T> extends LinkedList<T> {

    @Override
    public synchronized boolean add(T proceso) {
        return super.add(proceso);
    }

    @Override
    public synchronized boolean remove(Object proceso) {
        return super.remove(proceso);
    }
}
