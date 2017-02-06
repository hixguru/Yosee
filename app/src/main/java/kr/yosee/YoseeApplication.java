package kr.yosee;

import android.app.Application;
import android.content.Context;
import com.google.firebase.FirebaseApp;
import kr.yosee.dagger.module.AppModule;
import kr.yosee.dagger.view.AppComponent;
import kr.yosee.dagger.view.DaggerAppComponent;

/**
 * Created by hwanik on 2017. 1. 30..
 */

public class YoseeApplication extends Application {
    protected AppComponent component;

    public static YoseeApplication get(Context context) {
        return (YoseeApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        FirebaseApp.initializeApp(this);

        component = DaggerAppComponent.builder()
            .appModule(new AppModule(this))
            .build();

        component.inject(this);
    }

    public AppComponent getComponent() {
        return component;
    }
}
