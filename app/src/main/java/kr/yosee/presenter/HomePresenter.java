package kr.yosee.presenter;

/**
 * Created by hwanik on 2017. 1. 26..
 */

public interface HomePresenter {

    void initData();

    void getMoreRecipeInfo(String objectId);

    interface View {

        void showLoadingBar();

        void hideLoadingBar();

        void refresh();
    }
}
