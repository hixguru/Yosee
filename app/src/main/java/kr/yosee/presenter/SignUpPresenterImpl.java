package kr.yosee.presenter;

import com.parse.ParseUser;
import kr.yosee.util.Util;
import kr.yosee.view.SignUpActivity;

/**
 * Created by hwanik on 2017. 1. 31..
 */

public class SignUpPresenterImpl implements SignUpPresenter {

    private SignUpPresenter.View view;

    public SignUpPresenterImpl(SignUpActivity signUpActivity) {
        this.view = signUpActivity;
    }

    @Override
    public void signUp(String email, String password) {
        if (!Util.isPasswordValid(password)) {
            view.invalidPassword();
            return;
        }

        ParseUser user = new ParseUser();
        user.setUsername(email);
        user.setPassword(password);
        user.signUpInBackground(exception -> {
            if (exception == null) {
                view.onSuccessRegister(email, password);
                return;
            }
            view.onFailRegister();
        });
    }
}
