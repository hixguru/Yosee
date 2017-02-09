package kr.yosee.presenter;

import android.support.v4.app.Fragment;

/**
 * Created by hwanik on 2017. 2. 4..
 */

public interface UploadDetailCoverPresenter {
    void attachView(Fragment fragment);

    void detachView();

    void addNextSteop(Fragment fragment);

    interface View {
        void refresh();

        void setLastItem(int position);
    }
}
