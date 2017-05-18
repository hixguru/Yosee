package kr.yosee.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import javax.inject.Inject;
import kr.yosee.R;
import kr.yosee.adapter.RecyclerAdapter;
import kr.yosee.adapter.model.RecyclerDataModel;
import kr.yosee.adapter.view.ModelAdapterView;
import kr.yosee.dagger.module.HomeModule;
import kr.yosee.dagger.view.DaggerHomeComponent;
import kr.yosee.model.Recipe;
import kr.yosee.presenter.HomePresenter;

public class HomeTabFragment extends Fragment implements HomePresenter.View {

    private static final String TAG = HomeTabFragment.class.getSimpleName();
    @BindView(R.id.recycler_view) RecyclerView recyclerView;
    @Inject HomePresenter presenter;
    @Inject RecyclerDataModel<Recipe> recyclerDataModel;
    @Inject ModelAdapterView modelAdapterView;
    private RecyclerAdapter<Recipe> adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ProgressDialog dialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        layoutManager = new LinearLayoutManager(getContext());
        adapter = new RecyclerAdapter<>(getContext(), RecyclerAdapter.MAIN_VIEW);

        DaggerHomeComponent.builder()
            .homeModule(new HomeModule(this, adapter))
            .build()
            .inject(this);
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
            Recipe recipe = (Recipe) adapter1.getItem(position);
            presenter.getMoreRecipeInfo(recipe.mainStep.mainTitle);
        });

        presenter.initData();

        return view;
    }

    @Override
    public void showProgress() {
        dialog = ProgressDialog.show(getContext(), "레시피 로딩중", "잠시만 기다려주세요.", true, true);
    }

    @Override
    public void hideProgress() {
        dialog.dismiss();
    }

    @Override
    public void refresh() {
        modelAdapterView.refresh();
    }
}
