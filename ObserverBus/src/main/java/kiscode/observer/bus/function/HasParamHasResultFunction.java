package kiscode.observer.bus.function;

/**
* Description: 有参数有返回值
* Author: keno
* Date : 2021/10/27 16:15
**/
public abstract class HasParamHasResultFunction<P,R> extends Function {
    public HasParamHasResultFunction(String functionName) {
        super(functionName);
    }

    public abstract R function(P param);
}