package kr.yosee.dagger.view;

import dagger.Component;
import kr.yosee.dagger.module.HomeModule;
import kr.yosee.view.HomeTabFragment;

/**
 * Created by hwanik on 2017. 1. 27..
 */

@Component(modules = HomeModule.class)
public interface HomeComponent {
    void inject(HomeTabFragment homeTabFragment);
}
