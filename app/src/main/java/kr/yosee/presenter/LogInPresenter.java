package kr.yosee.presenter;

/**
 * Created by tta on 2017. 1. 26..
 */

public interface LogInPresenter {

    void login(String userId, String userPassword);

    interface View {
        void showProgress();

        void stopProgress();

        void invalidEmail();

        void invalidPassword();

        void onSuccessLogin();

        void onFailedLogin();
    }
}
