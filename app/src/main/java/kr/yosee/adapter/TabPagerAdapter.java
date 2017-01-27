package kr.yosee.adapter;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import kr.yosee.view.HomeTabFragment;
import kr.yosee.view.TabFragment;

/**
 * Created by hwanik on 2017. 1. 26..
 */

public class TabPagerAdapter extends FragmentStatePagerAdapter
    implements TabLayout.OnTabSelectedListener {

    private int tabSize;
    private ViewPager viewPager;

    public TabPagerAdapter(FragmentManager fm, int tabSize, ViewPager viewPager) {
        super(fm);
        this.tabSize = tabSize;
        this.viewPager = viewPager;
    }

    @Override public Fragment getItem(int position) {
        switch (position) {
            case 0:
                HomeTabFragment home = new HomeTabFragment();
                return home;
            case 1:
                TabFragment category = new TabFragment();
                return category;
            case 2:
                TabFragment search = new TabFragment();
                return search;
            case 3:
                TabFragment myPage = new TabFragment();
                return myPage;
            default:
                return null;
        }
    }

    @Override public int getCount() {
        return tabSize;
    }

    @Override public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override public void onTabUnselected(TabLayout.Tab tab) {
    }

    @Override public void onTabReselected(TabLayout.Tab tab) {
    }
}
