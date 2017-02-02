package kr.yosee.presenter;

import android.content.Intent;
import android.graphics.Bitmap;

/**
 * Created by hwanik on 2017. 2. 1..
 */

public interface UploadPresenter {
    void takePicture();

    void getPictureFromGallery();

    void navigateToDetailRecipe(Bitmap mainImage, String mainTitle, String mainDescription);

    interface View {
        void navigateToDetailPage(Intent intent);

        void showEmptyImage();

        void showEmptyTitle();
    }
}
