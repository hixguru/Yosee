package kr.yosee.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.parse.ParseUser;
import kr.yosee.R;
import kr.yosee.presenter.SignUpPresenter;
import kr.yosee.presenter.SignUpPresenterImpl;

public class SignUpActivity extends AppCompatActivity implements SignUpPresenter.View {

    private static final String TAG = SignUpActivity.class.getSimpleName();
    private SignUpPresenter presenter;
    @BindView(R.id.et_register_user_id) EditText userId;
    @BindView(R.id.et_register_user_password) EditText userPassword;

    @OnClick(R.id.btn_sign_up)
    void signUp() {
        presenter.signUp(userId.getText().toString(), userPassword.getText().toString());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        ButterKnife.bind(this);

        presenter = new SignUpPresenterImpl(this);
    }

    @Override
    public void invalidEmail() {
        Toast.makeText(this, "올바른 이메일 형식이 아닙니다.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void invalidPassword() {
        Toast.makeText(this, "비밀번호는 6자 이상으로 입력해야 합니다.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccessRegister(String email, String password) {
        ParseUser.logInInBackground(email, password, (user, e) -> {
            if (user == null) {
                finish();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }
            Log.e(TAG, "login: error > " + e.getMessage());
        });
    }

    @Override
    public void onFailRegister() {
        Toast.makeText(this, "회원가입에 실패했습니다.", Toast.LENGTH_SHORT).show();
    }
}
