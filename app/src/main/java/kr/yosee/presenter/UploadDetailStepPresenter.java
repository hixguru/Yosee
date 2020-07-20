package kr.yosee.presenter;

import android.graphics.Bitmap;
import kr.yosee.util.ActivityResultEvent;

/**
 * Created by hwanik on 2017. 2. 7..
 */

public interface UploadDetailStepPresenter {

    void takePicture();

    void getPictureFromGallery();

    void onActivityResult(ActivityResultEvent res);

    interface View {
        void setStepImage(Bitmap image);
    }
}
