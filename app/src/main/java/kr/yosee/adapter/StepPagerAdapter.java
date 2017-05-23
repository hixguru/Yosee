package kr.yosee.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
import kr.yosee.adapter.model.PagerDataModel;
import kr.yosee.adapter.view.ModelPagerView;
import kr.yosee.presenter.UploadDetailCoverPresenter;
import kr.yosee.view.UploadDetailCoverActivity;

/**
 * Created by hwanik on 2017. 2. 4..
 */

public class StepPagerAdapter extends FragmentStatePagerAdapter
    implements PagerDataModel, ModelPagerView {

    private UploadDetailCoverPresenter.View view;
    private ArrayList<Fragment> list;
    private int itemCount = 0;

    public StepPagerAdapter(FragmentManager fm, UploadDetailCoverActivity uploadDetailCoverActivity) {
        super(fm);
        this.view = uploadDetailCoverActivity;
        this.list = new ArrayList<>();
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return itemCount;
    }

    @Override
    public void addItem(Fragment fragment) {
        list.add(fragment);
        itemCount = list.size();
        view.refresh();
    }

    @Override
    public void removeItem() {
        list.remove(itemCount - 1);
        itemCount = list.size();
        view.refresh();
        view.setViewPagerPosition(itemCount - 1);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        list.remove(position);
        super.destroyItem(container, position, object);
    }

    @Override
    public void refresh() {
        view.refresh();
    }

    public List<Fragment> getSteps() {
        return list;
    }
}
