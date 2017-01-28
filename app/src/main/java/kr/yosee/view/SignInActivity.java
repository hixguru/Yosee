package kr.yosee.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.parse.Parse;
import kr.yosee.R;
import kr.yosee.presenter.SignInPresenter;
import kr.yosee.presenter.SignInPresenterImpl;

public class SignInActivity extends AppCompatActivity implements SignInPresenter.view {

    @BindView(R.id.et_user_id) EditText userId;
    @BindView(R.id.et_user_password) EditText userPassword;

    private SignInPresenter presenter;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        Parse.initialize(this, "USjhdBZW0Jsm8jvedZIoc4zm0OdZRvI0lMWNoRUt",
            "eUkreRV5NNa6iruqmLnbpTqVG6F5Z3MZDT0bWJxo");
        ButterKnife.bind(this);

        presenter = new SignInPresenterImpl(this);
    }

    @OnClick(R.id.btn_sign_in)
    public void signIn() {
        presenter.login(userId.getText().toString(), userPassword.getText().toString());
    }

    @Override
    public void emptyUserInfo() {
        Toast.makeText(this, "아이디 또는 비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void invalidUserInfo() {
        Toast.makeText(getApplicationContext(), "아이디와 비밀번호를 확인해주세요", Toast.LENGTH_SHORT).show();
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
    public void startMainActivity() {
        finish();
        Intent intent = new Intent(SignInActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
