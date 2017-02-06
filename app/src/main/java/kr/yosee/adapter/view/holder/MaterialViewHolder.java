package kr.yosee.adapter.view.holder;

import android.view.View;
import android.widget.EditText;
import butterknife.BindView;
import butterknife.ButterKnife;
import kr.yosee.R;
import kr.yosee.adapter.RecyclerAdapter;
import kr.yosee.adapter.view.BaseViewHolder;
import kr.yosee.model.Material;

/**
 * Created by hwanik on 2017. 2. 6..
 */

public class MaterialViewHolder extends BaseViewHolder<Material> {
    @BindView(R.id.et_mat_name) EditText matName;
    @BindView(R.id.et_mat_amount) EditText matAmount;
    @BindView(R.id.et_mat_unit) EditText matUnit;

    private final RecyclerAdapter adapter;
    private final View view;

    public MaterialViewHolder(RecyclerAdapter adapter, View view) {
        super(view);
        this.adapter = adapter;
        this.view = view;
        ButterKnife.bind(this, view);
    }

    @Override
    public void onBindView(Material item, int position) {

    }
}
