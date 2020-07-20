package kr.yosee.presenter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import java.io.IOException;
import kr.yosee.util.ActivityResultEvent;
import kr.yosee.view.UploadDetailStepFragment;

import static android.app.Activity.RESULT_OK;
import static kr.yosee.view.UploadDetailStepFragment.REQUEST_STEP_IMAGE_CAPTURE;
import static kr.yosee.view.UploadDetailStepFragment.REQUEST_STEP_IMAGE_FROM_GALLERY;

/**
 * Created by hwanik on 2017. 2. 7..
 */

public class UploadDetailStepPresenterImpl implements UploadDetailStepPresenter {

    private UploadDetailStepFragment fragment;
    private UploadDetailStepPresenter.View view;

    public UploadDetailStepPresenterImpl(UploadDetailStepFragment fragment) {
        this.fragment = fragment;
        this.view = fragment;
    }

    @Override
    public void takePicture() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(fragment.getActivity().getPackageManager()) != null) {
            fragment.getActivity().startActivityForResult(takePictureIntent, REQUEST_STEP_IMAGE_CAPTURE);
        }
    }

    @Override
    public void getPictureFromGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        fragment.getActivity().startActivityForResult(Intent.createChooser(intent, "Select File"),
                                                      REQUEST_STEP_IMAGE_FROM_GALLERY);
    }

    @Override
    public void onActivityResult(ActivityResultEvent res) {
        Bitmap image = null;
        if (res.getResultCode() == RESULT_OK && res.getRequestCode() == REQUEST_STEP_IMAGE_CAPTURE) {
            Bundle extras = res.getData().getExtras();
            image = (Bitmap) extras.get("data");
            view.setStepImage(image);
        }

        if (res.getResultCode() == RESULT_OK && res.getRequestCode() == REQUEST_STEP_IMAGE_FROM_GALLERY) {
            Uri uri = res.getData().getData();
            try {
                image = MediaStore.Images.Media.getBitmap(fragment.getActivity().getContentResolver(), uri);
                view.setStepImage(image);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
