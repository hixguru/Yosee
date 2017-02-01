package kr.yosee.dagger.module;

import android.content.Context;
import dagger.Module;
import dagger.Provides;
import kr.yosee.adapter.RecyclerAdapter;
import kr.yosee.adapter.model.RecipeDataModel;
import kr.yosee.adapter.view.RecipeAdapterView;
import kr.yosee.presenter.MainPresenter;
import kr.yosee.presenter.MainPresenterImpl;
import kr.yosee.util.DBHelper;
import kr.yosee.util.QueryGenerator;
import kr.yosee.view.HomeTabFragment;

/**
 * Created by hwanik on 2017. 1. 27..
 */

@Module
public class MainModule {

    private MainPresenter.View view;
    private RecyclerAdapter adapter;
    private Context context;

    public MainModule(HomeTabFragment homeTabFragment, RecyclerAdapter adapter) {
        this.view = homeTabFragment; // Upcasting HomeTabFragment > MainPresenter.View
        this.adapter = adapter;
        this.context = homeTabFragment.getContext();
    }

    @Provides
    MainPresenter providePresenter(MainPresenterImpl presenter) {
        return presenter;
    }

    @Provides
    MainPresenter.View provideView() {
        return view;
    }

    @Provides
    RecipeDataModel provideRecipeDataModel() {
        return adapter;
    }

    @Provides
    RecipeAdapterView provideRecipeAdaterView() {
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
