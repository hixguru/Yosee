package kr.yosee.presenter;

import android.support.v4.app.Fragment;
import android.text.TextUtils;
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
        view.setViewPagerPosition(adapter.getCount());
    }

    @Override
    public void addNextStep(Fragment fragment) {
        adapter.addItem(fragment);
        view.setViewPagerPosition(adapter.getCount());
    }

    @Override
    public void uploadRecipe() {
        byte[] mainImage = activity.getIntent().getByteArrayExtra("main_image");
        String mainTitle = activity.getIntent().getStringExtra("main_title");
        String mainDescription = activity.getIntent().getStringExtra("main_description");

        ParseFile mainImageFile = new ParseFile("main_image", mainImage);

        UploadDetailMaterialFragment firstStep = (UploadDetailMaterialFragment) adapter.getItem(0);
        List<Material> materialList = firstStep.getMaterialList();

        List<Fragment> steps = adapter.getSteps();

        if (!isReadyToUpload(firstStep)) return;

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

    private boolean isReadyToUpload(UploadDetailMaterialFragment firstStep) {
        String serving = firstStep.etServing.getText().toString();
        String cookingTime = firstStep.etCookingTime.getText().toString();
        List<Material> materialList = firstStep.getMaterialList();
        String tip = firstStep.etTip

        if (TextUtils.isEmpty(cookingTime)) {
            firstStep.etCookingTime.requestFocus();
            view.showEmptyItem("요리는 얼마나 걸리나요?");
            view.setViewPagerPosition(0);
            return false;
        }

        if (TextUtils.isEmpty(serving)) {
            firstStep.etServing.requestFocus();
            view.showEmptyItem("누구와 함께 먹을건가요?");
            view.setViewPagerPosition(0);
            return false;
        }

        if (materialList.size() == 0) {
            view.showEmptyItem("재료를 입력해주세요.");
            view.setViewPagerPosition(0);
            return false;
        }

        if (TextUtils.isEmpty(tip)) {
            view.showEmptyItem("알아두면 좋은 팁이 있을까요?");
            view.setViewPagerPosition(0);
            return false;
        }

        return true;
    }
}
