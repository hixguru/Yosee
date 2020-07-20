package kr.yosee;

import android.app.Application;
import android.content.Context;
import com.google.firebase.FirebaseApp;
import com.parse.Parse;
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
        Parse.initialize(new Parse.Configuration.Builder(this)
            .applicationId("KT8QEhPFxxDKAwLpile738MDRTWjwc2VuoUONLPn")
            .clientKey("vSHwnfGXGyHTJUEEndJhZI2dzKAjI1oLpxl96ZBn")
            .server("https://parseapi.back4app.com/").build()
        );

        component = DaggerAppComponent.builder()
            .appModule(new AppModule(this))
            .build();

        component.inject(this);
    }

    public AppComponent getComponent() {
        return component;
    }
}
