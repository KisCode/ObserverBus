package kiscode.observer.bus;

import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.Log;

import kiscode.observer.bus.function.Observer;
import kiscode.observer.bus.function.DataObserver;
import kiscode.observer.bus.function.NonDataObserver;

/**
 * Description: 观察接口总线
 * Author: keno
 * Date : 2021/10/28 11:10
 **/
public class ObserverBus {
    private static final String TAG = "ObserverBus";
    private static ObserverBus mInstance;
    private ArrayMap<String, Observer> mFunctionMap;

    private ObserverBus() {
        mFunctionMap = new ArrayMap<>();
    }

    public static ObserverBus getInstance() {
        if (mInstance == null) {
            mInstance = new ObserverBus();
        }
        return mInstance;
    }

    public void register(Observer function) {
        if (function == null)
            return;
        mFunctionMap.put(function.functionName, function);
    }

    public void unRegister(String functionName) {
        mFunctionMap.remove(functionName);
    }

    public void notify(String functionName) {
        if (TextUtils.isEmpty(functionName)) {
            return;
        }
        notify(functionName, null);
    }


    /**
     * 有参数无返回值方法执行
     *
     * @param functionName
     * @param param
     * @param
     */
    public <P> void notify(String functionName, P param) {
        Observer function = mFunctionMap.get(functionName);
        if (function == null) {
            throw new IllegalStateException("No found function " + functionName);
        }

        try {
            if (function instanceof DataObserver) {
                //参数合法性校验
                ((DataObserver) function).observer(param);
            } else if (function instanceof NonDataObserver) {
                ((NonDataObserver) function).observer();
            }
        } catch (Exception e) {
            Log.e(TAG, "invoke function " + functionName + " error, " + e);
        }
    }
}