package kiscode.observer.bus.function;

public abstract class DataObserver<T> extends Observer {
    public DataObserver(String functionName) {
        super(functionName);
    }

    public abstract void observer(T t);
}