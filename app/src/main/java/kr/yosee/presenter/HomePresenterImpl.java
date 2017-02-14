package kr.yosee.presenter;

import android.content.Context;
import android.util.Log;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import javax.inject.Inject;
import kr.yosee.adapter.model.RecyclerDataModel;
import kr.yosee.adapter.view.ModelAdapterView;
import kr.yosee.model.DetailRecipe;
import kr.yosee.model.Recipe;
import kr.yosee.util.Constants;
import kr.yosee.util.DBHelper;
import kr.yosee.util.QueryGenerator;

/**
 * Created by hwanik on 2017. 1. 26..
 */

public class HomePresenterImpl implements HomePresenter {

    private final String TAG = HomePresenter.class.getSimpleName();
    private final String TABLE_NAME = "test1";
    private final String MAIN_TITLE = "MAIN_TITLE";
    private final String SUB_TITLE = "SUB_TITLE";
    private final String MAIN_IMAGE = "MAIN_IMAGE";
    private HomePresenter.View view;
    private RecyclerDataModel recyclerDataModel;
    private ModelAdapterView modelAdapterView;
    private DBHelper dbHelper;
    private QueryGenerator queryGenerator;
    @Inject
    Context context;

    @Inject
    HomePresenterImpl(HomePresenter.View view, RecyclerDataModel recyclerDataModel,
                      ModelAdapterView modelAdapterView, DBHelper dbHelper,
                      QueryGenerator queryGenerator) {
        this.view = view;
        this.recyclerDataModel = recyclerDataModel;
        this.modelAdapterView = modelAdapterView;
        this.dbHelper = dbHelper;
        this.queryGenerator = queryGenerator;
    }

    @Override
    public void initData() {
        view.showLoadingBar();
        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Recipe recipe = dataSnapshot.getValue(Recipe.class);
                // Log.e(TAG, "onDataChange: recipe > " + recipe.mainStep.mainTitle);
                // recyclerDataModel.add(recipe);
                // view.refresh();
                view.hideLoadingBar();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        DatabaseReference reference =
            FirebaseDatabase.getInstance().getReference().child(Constants.RECIPES);
        reference.addValueEventListener(listener);

        // queryGenerator.initTable(TABLE_NAME);
        // queryGenerator.orderByDescending("updateAt");
        //
        // dbHelper.setQuery(queryGenerator.getQuery());
        // dbHelper.getResult()
        //     .subscribe(recipes -> {
        //         for (int i = 0; i < recipes.size(); i++) {
        //             ParseObject recipe = recipes.get(i);
        //             String image = ((ParseFile) recipes.get(i).get(MAIN_IMAGE)).getUrl();
        //
        //             // recyclerDataModel.add(
        //             //     new Recipe(image, recipe.getString(MAIN_TITLE), recipe.getString(SUB_TITLE),
        //             //                recipe.getObjectId()));
        //             recyclerDataModel.add(new Recipe("zz", "zz", "zz", "zz", null));
        //         }
        //         view.refresh();
        //         view.hideLoadingBar();
        //     }, e -> {
        //         view.hideLoadingBar();
        //         Log.e(TAG, "initData: e " + e.getMessage());
        //     });
        //
        // recyclerDataModel.add(new Recipe("zz", "zz", "zz", "zz", null));
        // recyclerDataModel.add(new Recipe("zz", "zz", "zz", "zz", null));
        // recyclerDataModel.add(new Recipe("zz", "zz", "zz", "zz", null));
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
                               Log.e(TAG, "getMoreRecipeInfo: "
                                   + detailRecipe.get(0).getString("M_NAME")
                                   + String.valueOf(k));
                               if (k == 3) break;
                               k++;
                           }
                       }, e -> Log.e(TAG, "getData: " + e.getMessage()),
                       () -> {
                           view.hideLoadingBar();
                           Log.e(TAG, "getMoreRecipeInfo: onComplete");
                       });
    }
}
