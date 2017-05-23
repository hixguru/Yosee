package kr.yosee.dagger.module;

import android.content.Context;
import dagger.Module;
import dagger.Provides;
import kr.yosee.adapter.RecyclerAdapter;
import kr.yosee.adapter.model.RecyclerDataModel;
import kr.yosee.adapter.view.ModelAdapterView;
import kr.yosee.model.Recipe;
import kr.yosee.presenter.HomePresenter;
import kr.yosee.presenter.HomePresenterImpl;
import kr.yosee.util.DBHelper;
import kr.yosee.util.QueryGenerator;
import kr.yosee.view.HomeTabFragment;

/**
 * Created by hwanik on 2017. 1. 27..
 */

@Module
public class HomeModule {

    private HomePresenter.View view;
    private RecyclerAdapter<Recipe> adapter;
    private Context context;

    public HomeModule(HomeTabFragment homeTabFragment, RecyclerAdapter<Recipe> adapter) {
        this.view = homeTabFragment; // Upcasting HomeTabFragment > HomePresenter.View
        this.adapter = adapter;
        this.context = homeTabFragment.getContext();
    }

    @Provides
    HomePresenter providePresenter(HomePresenterImpl presenter) {
        return presenter;
    }

    @Provides
    HomePresenter.View provideView() {
        return view;
    }

    @Provides
    RecyclerDataModel<Recipe> provideRecipeDataModel() {
        return adapter;
    }

    @Provides
    ModelAdapterView provideRecipeAdaterView() {
        return adapter;
    }

    @Provides
    DBHelper provideDBHelper() {
        return new DBHelper();
    }

    @Provides
    QueryGenerator provideQueryGerator() {
        return new QueryGenerator();
    }

    @Provides
    Context provideContext() {
        return context;
    }
}
