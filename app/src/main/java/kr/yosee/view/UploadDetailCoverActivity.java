package kr.yosee.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import java.util.ArrayList;
import kr.yosee.R;
import kr.yosee.adapter.StepPagerAdapter;
import kr.yosee.presenter.UploadDetailCoverPresenter;
import kr.yosee.presenter.UploadDetailCoverPresenterImpl;

public class UploadDetailCoverActivity extends AppCompatActivity {
    @BindView(R.id.step_view_pager) ViewPager viewPager;

    private UploadDetailCoverPresenter presenter;
    private ArrayList<Fragment> recipeStepList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_detail_cover);
        ButterKnife.bind(this);

        presenter = new UploadDetailCoverPresenterImpl(this, recipeStepList);

        byte[] byteToBitmap = getIntent().getByteArrayExtra("main_image");

        recipeStepList = new ArrayList<>();
        recipeStepList.add(new UploadDetailMaterialFragment().newInstance(byteToBitmap));
        recipeStepList.add(new UploadDetailStepFragment());

        StepPagerAdapter adapter = new StepPagerAdapter(getSupportFragmentManager(), this,
                                                        recipeStepList);
        viewPager.setAdapter(adapter);
    }
}
