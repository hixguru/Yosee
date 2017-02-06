package kr.yosee.presenter;

import com.google.firebase.auth.FirebaseAuth;
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
    public void signUp(String userEmail, String userPassword) {
        if (!Util.isEmailValid(userEmail)) {
            view.invalidEmail();
            return;
        }

        if (!Util.isPasswordValid(userPassword)) {
            view.invalidPassword();
            return;
        }

        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.createUserWithEmailAndPassword(userEmail, userPassword)
            .addOnCompleteListener(task -> view.onSuccessRegister());
    }
}
