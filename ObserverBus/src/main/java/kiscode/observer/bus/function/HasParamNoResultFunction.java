package kiscode.observer.bus.function;

/**
* Description: 有参数无返回值
* Author: keno
* Date : 2021/10/27 16:15
**/
public abstract class HasParamNoResultFunction<P> extends Function {
    public HasParamNoResultFunction(String functionName) {
        super(functionName);
    }

    public abstract void function(P param);
}