package dem.kiscode.magic.observer;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import dem.kiscode.magic.observer.pojo.User;
import kiscode.observer.bus.ObserverBus;
import kiscode.observer.bus.function.HasParamHasResultFunction;
import kiscode.observer.bus.function.HasParamNoResultFunction;
import kiscode.observer.bus.function.NoParamHasResultFunction;
import kiscode.observer.bus.function.NoParamNoResultFunction;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registerObserver();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (isFinishing()) {
            Log.i(TAG, "MainActivity is finishing,execute unRegisterObserver");
            unRegisterObserver();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void unRegisterObserver() {
        ObserverBus.getInstance().unRegister(Constants.FUNCTION_1);
        ObserverBus.getInstance().unRegister(Constants.FUNCTION_2);
        ObserverBus.getInstance().unRegister(Constants.FUNCTION_3);
        ObserverBus.getInstance().unRegister(Constants.FUNCTION_4);
        ObserverBus.getInstance().unRegister(Constants.FUNCTION_USER_CHANGE);
        ObserverBus.getInstance().unRegister(Constants.FUNCTION_USER_LIST_CHANGE);
    }

    private void registerObserver() {
        ObserverBus.getInstance().register(new NoParamNoResultFunction(Constants.FUNCTION_1) {
            @Override
            public void function() {
                Log.i(TAG, Constants.FUNCTION_1 + "\tNoParamNoResultFunction");
            }
        });

        ObserverBus.getInstance().register(new NoParamHasResultFunction<String>(Constants.FUNCTION_2) {
            @Override
            public String function() {
                Log.i(TAG, Constants.FUNCTION_2 + "\tNoParamHasResultFunction ，return type is String");
                return "调用了无参方法" + Constants.FUNCTION_2;
            }
        });

        ObserverBus.getInstance().register(new HasParamNoResultFunction<String>(Constants.FUNCTION_3) {

            @Override
            public void function(String param) {
                Log.i(TAG, Constants.FUNCTION_3 + "\tHasParamNoResultFunction , param value is " + param);
            }
        });

        ObserverBus.getInstance().register(new HasParamHasResultFunction<Integer, String>(Constants.FUNCTION_4) {
            @Override
            public String function(Integer param) {
                Log.i(TAG, Constants.FUNCTION_4 + "\tHasParamHasResultFunction , param value is " + param+" ，return type is String");
                return null;
            }
        });

        ObserverBus.getInstance().register(new HasParamNoResultFunction<User>(Constants.FUNCTION_USER_CHANGE) {
            @Override
            public void function(User user) {
                Log.i(TAG, Constants.FUNCTION_USER_CHANGE + "\tHasParamNoResultFunction , user is " + user);
            }
        });
        ObserverBus.getInstance().register(new HasParamNoResultFunction<List<User>>(Constants.FUNCTION_USER_LIST_CHANGE) {
            @Override
            public void function(List<User> userList) {
                Log.i(TAG, Constants.FUNCTION_USER_LIST_CHANGE + "\tHasParamNoResultFunction , user size is " + userList.size());
            }
        });
    }

    public void toNextPage(View view) {
        startActivity(new Intent(this, SecondActivity.class));
    }
}