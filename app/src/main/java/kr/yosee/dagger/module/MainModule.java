package kr.yosee.dagger.module;

import dagger.Module;
import dagger.Provides;
import kr.yosee.adapter.RecyclerAdapter;
import kr.yosee.adapter.model.RecipeDataModel;
import kr.yosee.adapter.view.RecipeAdapterView;
import kr.yosee.presenter.MainPresenter;
import kr.yosee.presenter.MainPresenterImpl;
import kr.yosee.util.DBHelper;
import kr.yosee.util.QueryGenerator;

/**
 * Created by hwanik on 2017. 1. 27..
 */

@Module
public class MainModule {

    private MainPresenter.View view;
    private RecyclerAdapter adapter;

    public MainModule(MainPresenter.View view, RecyclerAdapter adapter) {
        this.view = view;
        this.adapter = adapter;
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
    QueryGenerator provideQueryNegerator() {
        return new QueryGenerator();
    }
}
