package kr.yosee.presenter;

import android.util.Log;
import com.parse.ParseUser;
import kr.yosee.util.Util;
import kr.yosee.view.LogInActivity;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

/**
 * Created by tta on 2017. 1. 26..
 */

public class LogInPresenterImpl implements LogInPresenter {
    private LogInPresenter.View view;

    public LogInPresenterImpl(LogInActivity LogInActivity) {
        this.view = LogInActivity;
    }

    @Override
    public void login(String email, String password) {
        if (!Util.isPasswordValid(password)) {
            view.invalidPassword();
            return;
        }

        view.showProgress();

        ParseUser.logInInBackground(email, password, (user, e) -> {
            view.stopProgress();
            Log.e(TAG, "login: error > " + e.getMessage());

            if (user == null) {
                view.onFailedLogin();
                return;
            }
            view.onSuccessLogin();
        });
    }
}
