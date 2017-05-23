package kr.yosee.adapter.model;

import android.support.v4.app.Fragment;

/**
 * Created by hwanik on 2017. 2. 7..
 */

public interface PagerDataModel {
    void addItem(Fragment item);

    void removeItem();
}
