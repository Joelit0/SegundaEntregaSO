public abstract class Resource {

    public boolean enUso = false;
    public abstract void usar() throws InterruptedException;

    public abstract void soltar() throws InterruptedException;

}
