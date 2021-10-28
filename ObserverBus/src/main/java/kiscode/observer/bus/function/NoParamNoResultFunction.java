package kiscode.observer.bus.function;

public abstract class NoParamNoResultFunction extends Function {
    public NoParamNoResultFunction(String functionName) {
        super(functionName);
    }

    public abstract void function();
}