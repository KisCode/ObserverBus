package dem.kiscode.magic.observer;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import dem.kiscode.magic.observer.pojo.User;
import kiscode.observer.bus.ObserverBus;

public class SecondActivity extends AppCompatActivity {
    private static final String TAG = "SecondActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    public void executeNoParamNoResultFunction(View view) {
        ObserverBus.getInstance().notify(Constants.FUNCTION_1);
    }

    public void executeNoParamHasResultFunction(View view) {
        ObserverBus.getInstance().notify(Constants.FUNCTION_2);
    }

    public void executeUserChangeFunction(View view) {
        User user =new User("Java",55);
        ObserverBus.getInstance().notify(Constants.FUNCTION_USER_CHANGE,user);
    }
}