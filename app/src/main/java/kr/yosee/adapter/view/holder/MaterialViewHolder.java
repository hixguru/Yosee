package kr.yosee.adapter.view.holder;

import android.text.Editable;
import android.text.TextWatcher;
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
        Material material = (Material) adapter.getItem(position);
        matName.addTextChangedListener(new MaterialTextWatcher(material, MaterialTextWatcher.MATERIAL_NAME));
        matAmount.addTextChangedListener(new MaterialTextWatcher(material, MaterialTextWatcher.MATERIAL_AMOUNT));
        matUnit.addTextChangedListener(new MaterialTextWatcher(material, MaterialTextWatcher.MATERIAL_UNIT));
    }

    private class MaterialTextWatcher implements TextWatcher {
        static final int MATERIAL_NAME = 0;
        static final int MATERIAL_AMOUNT = 1;
        static final int MATERIAL_UNIT = 2;
        private Material material;
        private final int type;

        public MaterialTextWatcher(Material material, int type) {
            this.material = material;
            this.type = type;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            switch (type) {
                case MATERIAL_NAME:
                    material.matName = charSequence.toString();
                    break;
                case MATERIAL_AMOUNT:
                    material.matAmount = charSequence.toString();
                    break;
                case MATERIAL_UNIT:
                    material.matUnit = charSequence.toString();
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    }
}
