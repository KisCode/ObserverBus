package kiscode.observer.bus;

import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.Log;

import kiscode.observer.bus.function.Function;
import kiscode.observer.bus.function.HasParamHasResultFunction;
import kiscode.observer.bus.function.HasParamNoResultFunction;
import kiscode.observer.bus.function.NoParamHasResultFunction;
import kiscode.observer.bus.function.NoParamNoResultFunction;

/**
 * Description: 观察接口总线
 * Author: keno
 * Date : 2021/10/28 11:10
 **/
public class ObserverBus {
    private static final String TAG = "ObserverBus";
    private static ObserverBus mInstance;
    private ArrayMap<String, Function> mFunctionMap;

    private ObserverBus() {
        mFunctionMap = new ArrayMap<>();
    }

    public static ObserverBus getInstance() {
        if (mInstance == null) {
            mInstance = new ObserverBus();
        }
        return mInstance;
    }

    public void register(Function function) {
        if (function == null)
            return;
        mFunctionMap.put(function.functionName, function);
    }

    public void unRegister(String functionName) {
        mFunctionMap.remove(functionName);
    }

    public void invoke(String functionName) {
        if (TextUtils.isEmpty(functionName)) {
            return;
        }

        invoke(functionName, null, null);
    }


    public <P> void invoke(String functionName, P param) {
        invoke(functionName, param, null);
    }

    /**
     * 有参数无返回值方法执行
     *
     * @param functionName
     * @param param
     * @param <P>
     */
    private <P, R> R invoke(String functionName, P param, Class<R> resultClass) {
        Function function = mFunctionMap.get(functionName);
        if (function == null) {
            throw new IllegalStateException("No found function " + functionName);
        }

        try {
            if (function instanceof NoParamNoResultFunction) {
                ((NoParamNoResultFunction) function).function();
            } else if (function instanceof HasParamNoResultFunction) {
                ((HasParamNoResultFunction<P>) function).function(param);
            } else if (function instanceof NoParamHasResultFunction) {
                if (resultClass != null) {
                    return resultClass.cast(((NoParamHasResultFunction<P>) function).function());
                } else {
                    //如invoke调用方(被观察者)未传递返回值类型，则使用观察者泛型类型作为返回值类型
                    return (R) ((NoParamHasResultFunction<P>) function).function();
                }
            } else if (function instanceof HasParamHasResultFunction) {
                if (resultClass != null) {
                    return resultClass.cast(((HasParamHasResultFunction<P, R>) function).function(param));
                } else {
                    return (R) ((HasParamHasResultFunction<P, R>) function).function(param);
                }
            }
        } catch (Exception e) {
            Log.e(TAG, "invoke function " + functionName + " error, " + e);
        }
        return null;
    }
}