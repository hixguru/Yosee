package kr.yosee.presenter;

/**
 * Created by hwanik on 2017. 1. 31..
 */

public interface SignUpPresenter {

    void signUp(String email, String password);

    interface View {
        void invalidEmail();

        void invalidPassword();

        void onSuccessRegister(String email, String password);

        void onFailRegister();
    }
}
