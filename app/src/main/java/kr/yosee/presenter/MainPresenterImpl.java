package kr.yosee.presenter;

import android.util.Log;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import io.reactivex.Completable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.Subject;
import java.util.List;
import javax.inject.Inject;
import kr.yosee.adapter.model.RecipeDataModel;
import kr.yosee.adapter.view.RecipeAdapterView;

import kr.yosee.model.Recipe;
import kr.yosee.util.DBHelper;
import kr.yosee.util.QueryGenerator;

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
    MainPresenterImpl(MainPresenter.View view, RecipeDataModel recipeDataModel,
        RecipeAdapterView recipeAdapterView, DBHelper dbHelper, QueryGenerator queryGenerator) {
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
        dbHelper.getResult()
            .subscribe(recipes -> {
                for (int i = 0; i < recipes.size(); i++) {
                    ParseObject recipe = recipes.get(i);
                    String image = ((ParseFile) recipes.get(i).get(MAIN_IMAGE)).getUrl();

                    recipeDataModel.add(new Recipe(image, recipe.getString(MAIN_TITLE),
                        recipe.getString(SUB_TITLE), recipe.getObjectId()));
                }
                view.refresh();
                view.hideLoadingBar();
            }, e -> Log.e(TAG, "initData: e " + e.getMessage()));
    }

    @Override
    public void getMoreRecipeInfo(String objectId) {
        Completable.complete()
            .subscribeOn(Schedulers.io())
            .subscribe(() -> {
                getData(objectId);
            });
    }

    private void getData(String objectId) {
        dbHelper.whereEqualTo("objectId", objectId);
        dbHelper.getResult()
            .subscribe(detailRecipe -> {
                // TODO recipe detail data 처리 : D
                Log.e(TAG, "getData: size : " + detailRecipe.size());
            }, e -> Log.e(TAG, "getData: " + e.getMessage()));
    }
}
