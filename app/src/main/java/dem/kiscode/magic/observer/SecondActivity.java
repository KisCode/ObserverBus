package dem.kiscode.magic.observer;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

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
        ObserverBus.getInstance().invoke(Constants.FUNCTION_1);
    }

    public void executeNoParamHasResultFunction(View view) {
        ObserverBus.getInstance().invoke(Constants.FUNCTION_2, String.class);
    }


    public void executeHasParamNoResultFunction(View view) {
        String param = "参数A";
        ObserverBus.getInstance().invoke(Constants.FUNCTION_3, param);
    }

    public void executeHasParamHasResultFunction(View view) {
        int param = 18;
//        ObserverBus.getInstance().invoke(Constants.FUNCTION_4, param, String.class);
        ObserverBus.getInstance().invoke(Constants.FUNCTION_4, param);
    }

    public void executeUserChangeFunction(View view) {
        User user = new User();
        user.setName("Java");
        user.setAge(45);
        ObserverBus.getInstance().invoke(Constants.FUNCTION_USER_CHANGE, user);
    }

    public void executeUserListChangeFunction(View view) {
        List<User> userList = new ArrayList<>();
        User user = new User();
        user.setName("Java");
        user.setAge(45);

        User userC = new User();
        user.setName("C++");
        user.setAge(66);

        userList.add(user);
        userList.add(userC);
        ObserverBus.getInstance().invoke(Constants.FUNCTION_USER_LIST_CHANGE, userList);
    }
}