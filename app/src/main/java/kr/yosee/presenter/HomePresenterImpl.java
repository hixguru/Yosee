package kr.yosee.presenter;

import android.content.Context;
import android.util.Log;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import javax.inject.Inject;
import kr.yosee.adapter.model.RecyclerDataModel;
import kr.yosee.adapter.view.ModelAdapterView;
import kr.yosee.util.Constants;

/**
 * Created by hwanik on 2017. 1. 26..
 */

public class HomePresenterImpl implements HomePresenter {

    private final String TAG = HomePresenter.class.getSimpleName();
    private HomePresenter.View view;
    private RecyclerDataModel recyclerDataModel;
    private ModelAdapterView modelAdapterView;
    @Inject Context context;

    @Inject
    HomePresenterImpl(HomePresenter.View view, RecyclerDataModel recyclerDataModel,
                      ModelAdapterView modelAdapterView) {
        this.view = view;
        this.recyclerDataModel = recyclerDataModel;
        this.modelAdapterView = modelAdapterView;
    }

    @Override
    public void initData() {
        view.showLoadingBar();
        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //                Recipe recipe = dataSnapshot.getValue(List<Recipe>);
                Log.e(TAG, "onDataChange: snapshot " + dataSnapshot.getValue());
                // recyclerDataModel.add(recipe);
                // view.refresh();
                view.hideLoadingBar();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        DatabaseReference reference = FirebaseDatabase.getInstance()
            .getReference()
            .child(Constants.RECIPES);
        reference.addValueEventListener(listener);
    }

    @Override
    public void getMoreRecipeInfo(String objectId) {
        view.showLoadingBar();
    }
}
