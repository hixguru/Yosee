package kr.yosee.presenter;

import android.content.Context;
import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import java.util.List;
import javax.inject.Inject;
import kr.yosee.adapter.model.RecyclerDataModel;
import kr.yosee.adapter.view.ModelAdapterView;
import kr.yosee.model.AdditionalInfo;
import kr.yosee.model.MainStep;
import kr.yosee.model.Material;
import kr.yosee.model.Recipe;

/**
 * Created by hwanik on 2017. 1. 26..
 */

public class HomePresenterImpl implements HomePresenter {

    private final String TAG = HomePresenter.class.getSimpleName();
    private HomePresenter.View view;
    private RecyclerDataModel<Recipe> recyclerDataModel;
    private ModelAdapterView modelAdapterView;
    @Inject Context context;

    @Inject
    HomePresenterImpl(HomePresenter.View view, RecyclerDataModel<Recipe> recyclerDataModel,
                      ModelAdapterView modelAdapterView) {
        this.view = view;
        this.recyclerDataModel = recyclerDataModel;
        this.modelAdapterView = modelAdapterView;
    }

    @Override
    public void initData() {
        view.showProgress();

        ParseQuery<ParseObject> query = ParseQuery.getQuery("recipe1");
        query.setLimit(10);
        query.findInBackground((recipeList, e) -> {
            if (e != null) {
                Log.e(TAG, "initData: failed data load");
                view.hideProgress();
                return;
            }

            for (int i = 0; i < recipeList.size(); i++) {
                ParseObject item = recipeList.get(i);
                ParseFile mainImage = (ParseFile) item.get("main_image");

                String imgUrl = mainImage.getUrl();
                String mainTitle = String.valueOf(item.get("main_title"));
                String mainDescription = String.valueOf(item.get("main_description"));
                String cookingTime = String.valueOf(item.get("cooking_time"));
                String serving = String.valueOf(item.get("serving"));
                String tip = String.valueOf(item.get("tip"));

                List<Material> materials = new Gson().fromJson(String.valueOf(item.get("materials")),
                    new TypeToken<List<Material>>() {}.getType());

                Recipe recipe = new Recipe.Builder()
                    .main(new MainStep(mainTitle, mainDescription, imgUrl))
                    .additionalInfo(new AdditionalInfo(cookingTime, serving, tip))
                    .materials(materials)
                    .build();

                recyclerDataModel.add(recipe);
                view.refresh();
            }
            modelAdapterView.refresh();
            view.hideProgress();
        });
    }

    @Override
    public void getMoreRecipeInfo(String objectId) {
        view.showProgress();
    }

    @Override
    public void nagivateToMoreRecipe(Recipe recipe) {
        Log.e(TAG, "nagivateToMoreRecipe: " + new Gson().toJson(recipe));
    }
}
