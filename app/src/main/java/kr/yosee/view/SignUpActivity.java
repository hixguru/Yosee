package kr.yosee.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
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
    public void onSuccessRegister() {
        Toast.makeText(this, "등록 성공!", Toast.LENGTH_SHORT).show();
    }
}
