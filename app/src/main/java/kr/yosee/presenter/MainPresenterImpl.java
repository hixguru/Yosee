package kr.yosee.presenter;

import android.util.Log;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import kr.yosee.adapter.model.RecipeDataModel;
import kr.yosee.adapter.view.RecipeAdapterView;
import kr.yosee.model.Recipe;

/**
 * Created by hwanik on 2017. 1. 26..
 */

public class MainPresenterImpl implements MainPresenter {

    private static final String TABLE_NAME = "test1";
    private static final String MAIN_TITLE = "MAIN_TITLE";
    private static final String SUB_TITLE = "SUB_TITLE";
    private static final String MAIN_IMAGE = "MAIN_IMAGE";
    private MainPresenter.View view;
    private RecipeDataModel recipeDataModel;
    private RecipeAdapterView recipeAdapterView;

    @Inject
    public MainPresenterImpl(MainPresenter.View view, RecipeDataModel recipeDataModel,
        RecipeAdapterView recipeAdapterView) {
        this.view = view;
        this.recipeDataModel = recipeDataModel;
        this.recipeAdapterView = recipeAdapterView;
    }

    @Override
    public void initData() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery(TABLE_NAME);

        query.orderByDescending("updatedAt");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> parseObjects, ParseException e) {
                if (e == null) {
                    for (int i = 0; i < parseObjects.size(); i++) {
                        ParseObject object = parseObjects.get(i);
                        ParseFile image = (ParseFile) parseObjects.get(i).get(MAIN_IMAGE);

                        recipeDataModel.add(new Recipe(image.getUrl(), object.getString(MAIN_TITLE),
                            object.getString(SUB_TITLE), object.getObjectId()));
                    }
                    view.refresh();
                } else {
                    Log.d("error", e.getMessage());
                }
            }
        });
    }
}
