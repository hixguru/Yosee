package kr.yosee.adapter.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by hwanik on 2017. 2. 4..
 */

public abstract class BaseViewHolder<ITEM> extends RecyclerView.ViewHolder {
    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void onBindView(ITEM item);
}
