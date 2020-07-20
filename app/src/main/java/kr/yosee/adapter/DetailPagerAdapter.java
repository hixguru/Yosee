package kr.yosee.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import java.util.ArrayList;
import java.util.List;
import kr.yosee.adapter.model.PagerDataModel;
import kr.yosee.adapter.view.ModelPagerView;
import kr.yosee.view.DetailRecipeActivity;

/**
 * Created by hwanik on 2017. 5. 24..
 */

public class DetailPagerAdapter extends FragmentStatePagerAdapter
    implements PagerDataModel, ModelPagerView {

    private List<Fragment> items;
    private final DetailRecipeActivity activity;

    public DetailPagerAdapter(FragmentManager fm, DetailRecipeActivity activity) {
        super(fm);
        items = new ArrayList<>();
        this.activity = activity;
    }

    @Override
    public Fragment getItem(int position) {
        return items.get(position);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public void refresh() {
        notifyDataSetChanged();
    }

    @Override
    public void addItem(Fragment item) {
        items.add(item);
        notifyDataSetChanged();
    }

    @Override
    public void removeItem(int position) {
    }
}
