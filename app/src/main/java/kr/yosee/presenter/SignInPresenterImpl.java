package kr.yosee.presenter;

import android.text.TextUtils;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

/**
 * Created by tta on 2017. 1. 26..
 */

public class SignInPresenterImpl implements SignInPresenter {

    private SignInPresenter.view view;

    public SignInPresenterImpl(SignInPresenter.view view) {
        this.view = view;
    }

    @Override
    public void login(String userId, String userPassword) {
        if (TextUtils.isEmpty(userId) || TextUtils.isEmpty(userPassword)) {
            view.emptyUserInfo();
            return;
        }

        view.showProgress();

        ParseUser.logInInBackground(userId, userPassword, new LogInCallback() {
            @Override
            public void done(ParseUser parseUser, ParseException e) {
                if (parseUser != null) {
                    view.stopProgress();
                    view.startMainActivity();
                } else {
                    view.stopProgress();
                    view.invalidUserInfo();
                }
            }
        });
    }
}
