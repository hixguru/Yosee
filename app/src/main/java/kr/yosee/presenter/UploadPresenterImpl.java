package kr.yosee.presenter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.text.TextUtils;
import java.io.ByteArrayOutputStream;
import kr.yosee.view.UploadActivity;
import kr.yosee.view.UploadDetail;

import static kr.yosee.view.UploadActivity.REQUEST_IMAGE_CAPTURE;
import static kr.yosee.view.UploadActivity.REQUEST_IMAGE_FROM_GALLERY;

/**
 * Created by hwanik on 2017. 2. 1..
 */

public class UploadPresenterImpl implements UploadPresenter {

    private UploadActivity activity;
    private UploadPresenter.View view;

    public UploadPresenterImpl(UploadActivity uploadActivity) {
        this.activity = uploadActivity;
        this.view = uploadActivity;
    }

    @Override
    public void takePicture() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(activity.getPackageManager()) != null) {
            activity.startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    public void getPictureFromGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        activity.startActivityForResult(Intent.createChooser(intent, "Select File"),
                                        REQUEST_IMAGE_FROM_GALLERY);
    }

    @Override
    public void navigateToDetailRecipe(Bitmap mainImage, String mainTitle, String mainDescription) {
        if (mainImage == null) {
            view.showEmptyImage();
            return;
        }

        if (TextUtils.isEmpty(mainTitle)) {
            view.showEmptyTitle();
            return;
        }

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        mainImage.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] bitmapToByte = stream.toByteArray();

        Intent intent = new Intent(activity, UploadDetail.class);
        intent.putExtra("main_image", bitmapToByte);
        intent.putExtra("main_title", mainTitle);
        intent.putExtra("main_description", mainDescription);

        view.navigateToDetailPage(intent);
    }
}
