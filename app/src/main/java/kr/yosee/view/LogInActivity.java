package kr.yosee.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import kr.yosee.R;
import kr.yosee.presenter.LogInPresenter;
import kr.yosee.presenter.LogInPresenterImpl;

public class LogInActivity extends AppCompatActivity implements LogInPresenter.View {
    private static final String TAG = LogInActivity.class.getSimpleName();
    @BindView(R.id.et_user_id) EditText userId;
    @BindView(R.id.et_user_password) EditText userPassword;

    private LogInPresenter presenter;
    private ProgressDialog dialog;
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        ButterKnife.bind(this);

        presenter = new LogInPresenterImpl(this);
        auth = FirebaseAuth.getInstance();
        authListener = firebaseAuth -> {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            if (user != null) {
                Log.e(TAG, "onCreate: signed_in" + user.getUid());
            } else {
                Log.e(TAG, "onCreate: sign out");
            }
        };
    }

    @OnClick(R.id.btn_sign_in)
    public void signIn() {
        presenter.login(userId.getText().toString(), userPassword.getText().toString());
    }

    @OnClick(R.id.btn_go_sign_up)
    public void signUp() {
        finish();
        Intent intent = new Intent(LogInActivity.this, SignUpActivity.class);
        startActivity(intent);
    }

    @Override
    public void showProgress() {
        dialog = ProgressDialog.show(this, "계정 확인중", "잠시만 기다려주세요.", true, true);
    }

    @Override
    public void stopProgress() {
        dialog.dismiss();
    }

    @Override
    public void invalidEmail() {
        Toast.makeText(this, "올바른 이메일 형식이 아닙니다.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void invalidPassword() {
        Toast.makeText(getApplicationContext(), "비밀번호는 6자리 이상으로 입력해주세요.", Toast.LENGTH_SHORT)
            .show();
    }

    @Override
    public void onSuccessLogin() {
        Intent intent = new Intent(LogInActivity.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onFailedLogin() {
        Toast.makeText(this, "이메일 또는 비밀번호가 올바르지 않습니다.", Toast.LENGTH_SHORT).show();
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
