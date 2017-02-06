package kr.yosee.presenter;

/**
 * Created by hwanik on 2017. 1. 31..
 */

public interface SignUpPresenter {

    void signUp(String userEmail, String userPassword);

    interface View {
        void invalidEmail();

        void invalidPassword();

        void onSuccessRegister();
    }
}
