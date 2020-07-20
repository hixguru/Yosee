package kr.yosee.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import java.util.ArrayList;
import java.util.List;
import kr.yosee.R;
import kr.yosee.adapter.RecyclerAdapter;
import kr.yosee.model.Material;
import kr.yosee.util.Util;

public class UploadDetailMaterialFragment extends Fragment {
    @BindView(R.id.rv_mat) public RecyclerView recyclerView;
    @BindView(R.id.iv_bg_img) public ImageView ivBackground;
    @BindView(R.id.et_cooking_time) public EditText etCookingTime;
    @BindView(R.id.et_serving) public EditText etServing;
    @BindView(R.id.et_tip) public EditText etTip;

    private static final String ARG_MAIN_IMAGE = "param1";
    private byte[] image;
    private RecyclerAdapter<Material> adapter;
    private RecyclerView.LayoutManager layoutManager;

    public UploadDetailMaterialFragment() {
    }

    public static UploadDetailMaterialFragment newInstance(byte[] image) {
        UploadDetailMaterialFragment fragment = new UploadDetailMaterialFragment();

        Bundle args = new Bundle();
        args.putByteArray(ARG_MAIN_IMAGE, image);
        fragment.setArguments(args);
        return fragment;
    }

    @OnClick(R.id.iv_add_material)
    void addMaterial() {
        adapter.add(new Material("", "", ""));
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            image = getArguments().getByteArray(ARG_MAIN_IMAGE);
        }

        adapter = new RecyclerAdapter<>(getContext(), RecyclerAdapter.MATERIAL_VIEW);
        layoutManager = new LinearLayoutManager(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_upload_detail_material, container, false);
        ButterKnife.bind(this, view);

        ivBackground.setImageBitmap(Util.byteArrToBitmap(image));

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        if (adapter.getItemCount() == 0) {
            adapter.add(new Material("", "", ""));
            adapter.notifyDataSetChanged();
        }

        return view;
    }

    public List<Material> getMaterialList() {
        List<Material> list = new ArrayList<>();
        for (int i = 0; i < adapter.getItemCount(); i++) {
            list.add(adapter.getItem(i));
        }
        return list;
    }
}
