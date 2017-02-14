package kr.yosee.presenter;

import android.support.v4.app.Fragment;
import android.util.Base64;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.List;
import kr.yosee.adapter.StepPagerAdapter;
import kr.yosee.model.Material;
import kr.yosee.model.Recipe;
import kr.yosee.util.Constants;
import kr.yosee.view.UploadDetailCoverActivity;
import kr.yosee.view.UploadDetailMaterialFragment;

/**
 * Created by hwanik on 2017. 2. 4..
 */

public class UploadDetailCoverPresenterImpl implements UploadDetailCoverPresenter {
    private final UploadDetailCoverActivity activity;
    private final UploadDetailCoverPresenter.View view;
    private StepPagerAdapter adapter;
    private DatabaseReference database;

    public UploadDetailCoverPresenterImpl(UploadDetailCoverActivity activity,
                                          StepPagerAdapter adapter) {
        this.activity = activity;
        this.view = activity;
        this.adapter = adapter;
        this.database = FirebaseDatabase.getInstance().getReference();
    }

    @Override
    public void attachView(Fragment fragment) {
        adapter.addItem(fragment);
    }

    @Override
    public void detachView() {
        adapter.removeItem();
        view.setLastItem(adapter.getCount());
    }

    @Override
    public void addNextStep(Fragment fragment) {
        adapter.addItem(fragment);
        view.setLastItem(adapter.getCount());
    }

    @Override
    public void uploadRecipe() {
        byte[] mainImage = activity.getIntent().getByteArrayExtra("main_image");
        String imageEncoded = Base64.encodeToString(mainImage, Base64.DEFAULT);
        String mainTitle = activity.getIntent().getStringExtra("main_title");
        String mainDescription = activity.getIntent().getStringExtra("main_description");

        UploadDetailMaterialFragment firstStep = (UploadDetailMaterialFragment) adapter.getItem(0);
        List<Material> materialList = firstStep.getMaterialList();

        Recipe recipe =
            new Recipe.Builder(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .main(new Recipe.MainStep(mainTitle, mainDescription, imageEncoded))
                .mat(materialList)
                .build();

        // Recipe recipe = new Recipe(FirebaseAuth.getInstance().getCurrentUser().getUid(), mainTitle,
        //                            mainDescription, imageEncoded, materialList);

        database.child(Constants.RECIPES).setValue(recipe.toMap());
    }
}
