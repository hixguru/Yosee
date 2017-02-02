package kr.yosee.util;

import android.text.TextUtils;

/**
 * Created by hwanik on 2017. 1. 31..
 */

public class Util {
    public static boolean isPasswordValid(String userPassword) {
        return !TextUtils.isEmpty(userPassword) && userPassword.length() >= 6;
    }

    public static boolean isEmailValid(String Email) {
        return !TextUtils.isEmpty(Email) && android.util.Patterns.EMAIL_ADDRESS.matcher(Email).matches();
    }
}
