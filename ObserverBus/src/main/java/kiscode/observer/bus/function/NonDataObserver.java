package kiscode.observer.bus.function;

public abstract class NonDataObserver extends Observer {
    public NonDataObserver(String functionName) {
        super(functionName);
    }

    public abstract void observer();

}