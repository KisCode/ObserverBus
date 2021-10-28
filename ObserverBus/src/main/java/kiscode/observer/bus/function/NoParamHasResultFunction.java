package kiscode.observer.bus.function;

/**
* Description: 无参数有返回值
* Author: keno
* Date : 2021/10/27 16:15
**/
public abstract class NoParamHasResultFunction<T> extends Function {
    public NoParamHasResultFunction(String functionName) {
        super(functionName);
    }

    public abstract T function();
}