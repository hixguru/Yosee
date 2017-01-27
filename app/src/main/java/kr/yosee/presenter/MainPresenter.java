package kr.yosee.presenter;

/**
 * Created by hwanik on 2017. 1. 26..
 */

public interface MainPresenter {

    void initData();

    interface View {
        void refresh();
    }
}
