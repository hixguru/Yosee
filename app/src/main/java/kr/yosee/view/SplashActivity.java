package kr.yosee.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import io.reactivex.Maybe;
import java.util.concurrent.TimeUnit;
import kr.yosee.R;

public class SplashActivity extends AppCompatActivity {

    private static final String TAG = SplashActivity.class.getSimpleName();
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        auth = FirebaseAuth.getInstance();
        authListener = firebaseAuth -> {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            Maybe.fromCallable(() -> user)
                .delay(2, TimeUnit.SECONDS)
                .subscribe(
                    currentUser -> {
                        Log.e(TAG, "onCreate: uid > " + currentUser.getUid());
                        goToActivity(MainActivity.class);
                    },
                    Throwable::printStackTrace,
                    () -> goToActivity(LogInActivity.class));
        };
    }

    public void goToActivity(Class target) {
        finish();
        Intent intent = new Intent(SplashActivity.this, target);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        auth.addAuthStateListener(authListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (authListener != null) {
            auth.removeAuthStateListener(authListener);
        }
    }
}
