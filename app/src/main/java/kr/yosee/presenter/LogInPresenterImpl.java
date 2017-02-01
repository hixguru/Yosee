package kr.yosee.presenter;

import com.google.firebase.auth.FirebaseAuth;
import kr.yosee.util.Util;
import kr.yosee.view.LogInActivity;

/**
 * Created by tta on 2017. 1. 26..
 */

public class LogInPresenterImpl implements LogInPresenter {
    private LogInActivity LogInActivity;
    private LogInPresenter.View view;
    private FirebaseAuth auth;

    public LogInPresenterImpl(LogInActivity LogInActivity) {
        this.view = LogInActivity;
        this.LogInActivity = LogInActivity;
    }

    @Override
    public void login(String userEmail, String userPassword) {
        auth = FirebaseAuth.getInstance();

        if (!Util.isValidEmail(userEmail)) {
            view.invalidEmail();
            return;
        }

        if (!Util.isValidPassword(userPassword)) {
            view.invalidPassword();
            return;
        }

        view.showProgress();
        auth.signInWithEmailAndPassword(userEmail, userPassword)
            .addOnCompleteListener(LogInActivity, task -> {
                view.stopProgress();
                if (!task.isSuccessful()) {
                    view.onFailedLogin();
                    return;
                }
                view.onSuccessLogin();
            });
    }
}
