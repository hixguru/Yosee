package kr.yosee.presenter;

import android.support.v4.app.Fragment;
import android.util.Log;
import com.google.gson.Gson;
import com.parse.ParseFile;
import com.parse.ParseObject;
import java.util.List;
import kr.yosee.adapter.StepPagerAdapter;
import kr.yosee.model.Material;
import kr.yosee.util.Util;
import kr.yosee.view.UploadDetailCoverActivity;
import kr.yosee.view.UploadDetailMaterialFragment;
import kr.yosee.view.UploadDetailStepFragment;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

/**
 * Created by hwanik on 2017. 2. 4..
 */

public class UploadDetailCoverPresenterImpl implements UploadDetailCoverPresenter {
    private final UploadDetailCoverActivity activity;
    private final UploadDetailCoverPresenter.View view;
    private StepPagerAdapter adapter;

    public UploadDetailCoverPresenterImpl(UploadDetailCoverActivity activity,
                                          StepPagerAdapter adapter) {
        this.activity = activity;
        this.view = activity;
        this.adapter = adapter;
    }

    @Override
    public void attachView(Fragment fragment) {
        adapter.addItem(fragment);
    }

    @Override
    public void detachView() {
        adapter.removeItem();
        view.setLastItem(adapter.getCount());
    }

    @Override
    public void addNextStep(Fragment fragment) {
        adapter.addItem(fragment);
        view.setLastItem(adapter.getCount());
    }

    @Override
    public void uploadRecipe() {
        byte[] mainImage = activity.getIntent().getByteArrayExtra("main_image");
        String mainTitle = activity.getIntent().getStringExtra("main_title");
        String mainDescription = activity.getIntent().getStringExtra("main_description");

        UploadDetailMaterialFragment firstStep = (UploadDetailMaterialFragment) adapter.getItem(0);

        List<Material> materialList = firstStep.getMaterialList();
        ParseFile mainImageFile = new ParseFile("main_image", mainImage);

        List<Fragment> steps = adapter.getSteps();

        ParseObject post = new ParseObject("recipe1");
        post.put("main_image", mainImageFile);
        post.put("main_title", mainTitle);
        post.put("main_description", mainDescription);
        post.put("serving", firstStep.etServing.getText().toString());
        post.put("cooking_time", firstStep.etCookingTime.getText().toString());
        post.put("tip", firstStep.etTip.getText().toString());
        post.put("materials", new Gson().toJson(materialList));
        for (int i = 1; i < steps.size(); i++) {
            UploadDetailStepFragment step = (UploadDetailStepFragment) steps.get(i);
            ParseFile stepImage = new ParseFile("step_" + i, Util.bitmapToByteArr(step.stepBitmapImage));
            post.put("step_image_" + (i + 1), stepImage);
            post.put("step_description_" + (i +1), step.stepDescription.getText().toString());
        }

        post.saveInBackground(e -> {
            if (e == null) {
                Log.e(TAG, "uploadRecipe: upload 성공");
                view.onSuccessUpload();
            }
        });
    }
}
