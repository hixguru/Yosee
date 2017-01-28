package kr.yosee.presenter;

/**
 * Created by tta on 2017. 1. 26..
 */

public interface SignInPresenter {

    void login(String userId, String userPassword);

    interface view {
        void emptyUserInfo();

        void invalidUserInfo();

        void showProgress();

        void stopProgress();

        void startMainActivity();
    }
}
