package kr.yosee.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.parse.ParseUser;
import kr.yosee.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ParseUser user = ParseUser.getCurrentUser();

        if (user != null) {
            goToActivity(MainActivity.class);
            return;
        }

        goToActivity(LogInActivity.class);
    }

    public void goToActivity(Class target) {
        finish();
        Intent intent = new Intent(SplashActivity.this, target);
        startActivity(intent);
    }
}
