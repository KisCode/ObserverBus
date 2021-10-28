package dem.kiscode.magic.observer;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import dem.kiscode.magic.observer.pojo.User;
import kiscode.observer.bus.ObserverBus;
import kiscode.observer.bus.function.DataObserver;
import kiscode.observer.bus.function.NonDataObserver;

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
        ObserverBus.getInstance().unRegister(Constants.FUNCTION_USER_CHANGE);
    }

    private void registerObserver() {
        //无数据监听
        ObserverBus.getInstance().register(new NonDataObserver(Constants.FUNCTION_1) {
            @Override
            public void observer() {
                Log.i(TAG, Constants.FUNCTION_1 + "\tNonDataObserver");
            }
        });

        //有数据监听
        ObserverBus.getInstance().register(new DataObserver<String>(Constants.FUNCTION_2) {
            @Override
            public void observer(String data) {
                Log.i(TAG, Constants.FUNCTION_2 + "\tDataObserver,param  is " + data);
            }
        });

        //有数据监听
        ObserverBus.getInstance().register(new DataObserver<User>(Constants.FUNCTION_USER_CHANGE) {
            @Override
            public void observer(User user) {
                Log.i(TAG, functionName + "\tDataObserver,user is " + user.toString());
            }
        });
    }

    public void toNextPage(View view) {
        startActivity(new Intent(this, SecondActivity.class));
    }
}