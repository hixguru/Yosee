package kr.yosee.dagger.view;

import dagger.Component;
import kr.yosee.dagger.module.MainModule;
import kr.yosee.view.HomeTabFragment;

/**
 * Created by hwanik on 2017. 1. 27..
 */

@Component(modules = MainModule.class)
public interface MainComponent {
    void inject(HomeTabFragment homeTabFragment);
}
