package kr.yosee.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.parse.Parse;
import javax.inject.Inject;
import kr.yosee.R;
import kr.yosee.YoseeApplication;
import kr.yosee.adapter.RecyclerAdapter;
import kr.yosee.adapter.model.RecipeDataModel;
import kr.yosee.adapter.view.RecipeAdapterView;
import kr.yosee.dagger.module.MainModule;
import kr.yosee.dagger.view.DaggerMainComponent;
import kr.yosee.presenter.MainPresenter;

public class HomeTabFragment extends Fragment implements MainPresenter.View {

    private static final String TAG = HomeTabFragment.class.getSimpleName();
    @BindView(R.id.recycler_view) RecyclerView recyclerView;
    @Inject MainPresenter presenter;
    @Inject RecipeDataModel recipeDataModel;
    @Inject RecipeAdapterView recipeAdapterView;
    private RecyclerAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ProgressDialog dialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Parse.initialize(getContext(), "USjhdBZW0Jsm8jvedZIoc4zm0OdZRvI0lMWNoRUt",
                         "eUkreRV5NNa6iruqmLnbpTqVG6F5Z3MZDT0bWJxo");

        layoutManager = new LinearLayoutManager(getContext());
        adapter = new RecyclerAdapter(getContext());

        DaggerMainComponent.builder()
            .mainModule(new MainModule(this, adapter))
            .build()
            .inject(this);

        Log.e(TAG, "onCreate: " + YoseeApplication.get(getActivity()).getComponent());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home_tab, container, false);
        ButterKnife.bind(this, view);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        adapter.setOnRecyclerItemClickListener((adapter1, position) -> {
            presenter.getMoreRecipeInfo(adapter1.getItem(position).getObjectId());
        });

        presenter.initData();

        return view;
    }

    @Override
    public void showLoadingBar() {
        dialog = ProgressDialog.show(getContext(), "레시피 로딩중", "잠시만 기다려주세요.", true, true);
    }

    @Override
    public void hideLoadingBar() {
        dialog.dismiss();
    }

    @Override
    public void refresh() {
        recipeAdapterView.refresh();
    }
}
