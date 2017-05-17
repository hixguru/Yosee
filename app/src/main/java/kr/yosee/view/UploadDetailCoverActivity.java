package kr.yosee.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.subjects.PublishSubject;
import kr.yosee.R;
import kr.yosee.adapter.StepPagerAdapter;
import kr.yosee.presenter.UploadDetailCoverPresenter;
import kr.yosee.presenter.UploadDetailCoverPresenterImpl;
import kr.yosee.util.ActivityResultEvent;

public class UploadDetailCoverActivity extends AppCompatActivity
    implements UploadDetailCoverPresenter.View {

    @BindView(R.id.step_view_pager) ViewPager viewPager;
    @BindView(R.id.toolbar) Toolbar toolbar;

    private UploadDetailCoverPresenterImpl presenter;
    private StepPagerAdapter adapter;
    private Menu menu;

    public PublishSubject<ActivityResultEvent> subject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_detail_cover);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        byte[] byteToBitmap = getIntent().getByteArrayExtra("main_image");
        String mainImage = getIntent().getStringExtra("main_title");
        String mainDescription = getIntent().getStringExtra("main_description");

        subject = PublishSubject.create();

        adapter = new StepPagerAdapter(getSupportFragmentManager(), this);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(3);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    menu.findItem(R.id.action_remove).setVisible(false);
                    menu.findItem(R.id.action_complete).setVisible(false);
                } else if (position == 1 && position == adapter.getCount() - 1) {
                    menu.findItem(R.id.action_complete).setVisible(true);
                } else if (position > 2) {
                    if (position == adapter.getCount() - 1) {
                        menu.findItem(R.id.action_remove).setVisible(true);
                        menu.findItem(R.id.action_complete).setVisible(true);
                    } else {
                        menu.findItem(R.id.action_remove).setVisible(true);
                        menu.findItem(R.id.action_complete).setVisible(false);
                    }
                } else {
                    menu.findItem(R.id.action_remove).setVisible(false);
                    menu.findItem(R.id.action_complete).setVisible(false);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        presenter = new UploadDetailCoverPresenterImpl(this, adapter);
        presenter.attachView(UploadDetailMaterialFragment.newInstance(byteToBitmap));
        presenter.attachView(UploadDetailStepFragment.newInstance());
    }

    @Override
    public void refresh() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setLastItem(int position) {
        if (position <= adapter.getCount()) {
            viewPager.setCurrentItem(position);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.upload_menu, menu);
        this.menu = menu;
        menu.findItem(R.id.action_remove).setVisible(false);
        menu.findItem(R.id.action_complete).setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_remove:
                presenter.detachView();
                return true;
            case R.id.action_complete:
                presenter.uploadRecipe();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public UploadDetailCoverPresenter getPresenter() {
        return presenter;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        subject.onNext(new ActivityResultEvent(requestCode, resultCode, data));
    }
}
