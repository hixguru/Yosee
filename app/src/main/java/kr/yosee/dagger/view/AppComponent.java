package kr.yosee.dagger.view;

import dagger.Component;
import kr.yosee.YoseeApplication;
import kr.yosee.dagger.module.AppModule;

/**
 * Created by hwanik on 2017. 1. 30..
 */
@Component(modules = AppModule.class)
public interface AppComponent {
    void inject(YoseeApplication yoseeApplication);
}
