package kr.yosee.presenter;

import android.support.v4.app.Fragment;
import kr.yosee.adapter.StepPagerAdapter;
import kr.yosee.view.UploadDetailCoverActivity;

/**
 * Created by hwanik on 2017. 2. 4..
 */

public class UploadDetailCoverPresenterImpl implements UploadDetailCoverPresenter {
    private final UploadDetailCoverActivity uploadDetailCoverActivity;
    private final UploadDetailCoverPresenter.View view;
    private StepPagerAdapter adapter;

    public UploadDetailCoverPresenterImpl(UploadDetailCoverActivity uploadDetailCoverActivity,
                                          StepPagerAdapter adapter) {
        this.uploadDetailCoverActivity = uploadDetailCoverActivity;
        this.view = uploadDetailCoverActivity;
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
    public void addNextSteop(Fragment fragment) {
        adapter.addItem(fragment);
        view.setLastItem(adapter.getCount());
    }
}
