package kr.yosee.presenter;

import android.support.v4.app.Fragment;
import java.util.ArrayList;
import kr.yosee.view.UploadDetailCoverActivity;

/**
 * Created by hwanik on 2017. 2. 4..
 */

public class UploadDetailCoverPresenterImpl implements UploadDetailCoverPresenter {
    private final UploadDetailCoverActivity uploadDetailCoverActivity;
    private final ArrayList<Fragment> recipeStepList;

    public UploadDetailCoverPresenterImpl(UploadDetailCoverActivity uploadDetailCoverActivity,
                                          ArrayList<Fragment> recipeStepList) {

        this.uploadDetailCoverActivity = uploadDetailCoverActivity;
        this.recipeStepList = recipeStepList;
    }

    @Override
    public void attachView() {
    }
}
