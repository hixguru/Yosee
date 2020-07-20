package kr.yosee.presenter;

import kr.yosee.model.Recipe;

/**
 * Created by hwanik on 2017. 1. 26..
 */

public interface HomePresenter {

    void initData();

    void getMoreRecipeInfo(String objectId);

    void nagivateToMoreRecipe(Recipe recipe);

    interface View {

        void showProgress();

        void hideProgress();

        void refresh();
    }
}
