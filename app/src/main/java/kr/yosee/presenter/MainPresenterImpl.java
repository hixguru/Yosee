package kr.yosee.presenter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.parse.ParseFile;
import com.parse.ParseObject;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import javax.inject.Inject;
import kr.yosee.adapter.model.RecipeDataModel;
import kr.yosee.adapter.view.RecipeAdapterView;
import kr.yosee.model.DetailRecipe;
import kr.yosee.model.Recipe;
import kr.yosee.util.DBHelper;
import kr.yosee.util.QueryGenerator;
import kr.yosee.view.SignInActivity;

/**
 * Created by hwanik on 2017. 1. 26..
 */

public class MainPresenterImpl implements MainPresenter {

    private final String TAG = MainPresenter.class.getSimpleName();
    private final String TABLE_NAME = "test1";
    private final String MAIN_TITLE = "MAIN_TITLE";
    private final String SUB_TITLE = "SUB_TITLE";
    private final String MAIN_IMAGE = "MAIN_IMAGE";
    private MainPresenter.View view;
    private RecipeDataModel recipeDataModel;
    private RecipeAdapterView recipeAdapterView;
    private DBHelper dbHelper;
    private QueryGenerator queryGenerator;
    @Inject
    Context context;

    @Inject
    MainPresenterImpl(MainPresenter.View view, RecipeDataModel recipeDataModel,
                      RecipeAdapterView recipeAdapterView, DBHelper dbHelper,
                      QueryGenerator queryGenerator) {
        this.view = view;
        this.recipeDataModel = recipeDataModel;
        this.recipeAdapterView = recipeAdapterView;
        this.dbHelper = dbHelper;
        this.queryGenerator = queryGenerator;
    }

    @Override
    public void initData() {
        view.showLoadingBar();
        queryGenerator.initTable(TABLE_NAME);
        queryGenerator.orderByDescending("updateAt");

        dbHelper.setQuery(queryGenerator.getQuery());
        dbHelper.getResult().subscribe(recipes -> {
            for (int i = 0; i < recipes.size(); i++) {
                ParseObject recipe = recipes.get(i);
                String image = ((ParseFile) recipes.get(i).get(MAIN_IMAGE)).getUrl();

                recipeDataModel.add(
                    new Recipe(image, recipe.getString(MAIN_TITLE), recipe.getString(SUB_TITLE),
                               recipe.getObjectId()));
            }
            view.refresh();
            view.hideLoadingBar();
        }, e -> Log.e(TAG, "initData: e " + e.getMessage()));
    }

    @Override
    public void getMoreRecipeInfo(String objectId) {
        view.showLoadingBar();
        dbHelper.whereEqualTo("objectId", objectId);
        dbHelper.getResult()
            .subscribeOn(Schedulers.io())
            .subscribe(detailRecipe -> {
                ArrayList<DetailRecipe> matList = new ArrayList<>();

                int k = 0;
                while (detailRecipe.get(0).getString("M_NAME") + String.valueOf(k) != null) {
                    matList.add(
                        new DetailRecipe(
                            detailRecipe.get(0).getString("M_NAME" + String.valueOf(k)),
                            detailRecipe.get(0).getString("M_NULL" + String.valueOf(k)),
                            detailRecipe.get(0).getString("M_UNIT_" + String.valueOf(k))));
                    k++;
                }
            }, e -> {
                Log.e(TAG, "getData: " + e.getMessage());
            }, () -> {
                view.hideLoadingBar();
                Log.e(TAG, "getMoreRecipeInfo: onComplete");
            });
    }
}
