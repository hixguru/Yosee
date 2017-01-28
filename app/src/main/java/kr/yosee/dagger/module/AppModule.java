package kr.yosee.dagger.module;

import android.app.Application;
import android.content.Context;
import dagger.Module;
import dagger.Provides;

/**
 * Created by hwanik on 2017. 1. 27..
 */

@Module
public class AppModule {

    private final Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    public Context provideApplicationContext() {
        return application;
    }
}
