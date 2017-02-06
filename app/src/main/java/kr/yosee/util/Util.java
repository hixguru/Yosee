package kr.yosee.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import java.io.ByteArrayOutputStream;

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

    public static byte[] bitmapToByteArr(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        return stream.toByteArray();
    }

    public static Bitmap byteArrToBitmap(byte[] byteArr) {
        return BitmapFactory.decodeByteArray(byteArr, 0, byteArr.length);
    }
}
