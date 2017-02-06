package kr.yosee.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import java.util.ArrayList;
import kr.yosee.view.UploadDetailCoverActivity;

/**
 * Created by hwanik on 2017. 2. 4..
 */

public class StepPagerAdapter extends FragmentStatePagerAdapter {

    private ArrayList<Fragment> list;

    public StepPagerAdapter(FragmentManager fm, UploadDetailCoverActivity uploadDetailCoverActivity,
                            ArrayList<Fragment> stepList) {
        super(fm);
        this.list = stepList;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
