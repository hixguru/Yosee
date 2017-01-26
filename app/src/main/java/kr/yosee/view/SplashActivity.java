package kr.yosee.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.parse.Parse;
import com.parse.ParseUser;
import io.reactivex.Maybe;
import java.util.concurrent.TimeUnit;
import kr.yosee.R;

public class SplashActivity extends AppCompatActivity {

    private static final String TAG = SplashActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Parse.initialize(this, "USjhdBZW0Jsm8jvedZIoc4zm0OdZRvI0lMWNoRUt",
            "eUkreRV5NNa6iruqmLnbpTqVG6F5Z3MZDT0bWJxo");

        ParseUser currentUser = ParseUser.getCurrentUser();

        Maybe.fromCallable(() -> currentUser)
            .delay(2, TimeUnit.SECONDS)
            .subscribe(
                user -> goToActivity(MainActivity.class),
                Throwable::printStackTrace,
                () -> goToActivity(SignInActivity.class)
            );
    }

    public void goToActivity(Class target) {
        finish();
        Intent intent = new Intent(SplashActivity.this, target);
        startActivity(intent);
    }
}
