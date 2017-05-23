package io.toru.instagramsearch.app;

import android.app.Application;

import io.toru.instagramsearch.di.component.DaggerMainComponent;
import io.toru.instagramsearch.di.component.MainComponent;
import io.toru.instagramsearch.di.module.NetworkModule;

/**
 * Created by wonyoung on 2017. 5. 23..
 */

public class InstagramSearchApplication extends Application {
    private static InstagramSearchApplication app;

    private MainComponent mainComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;

        initDependencyInjection();
    }

    public static InstagramSearchApplication getApplication(){
        return app;
    }

    private void initDependencyInjection(){
        mainComponent = DaggerMainComponent.builder()
                .networkModule(new NetworkModule())
                .build();
    }

    public MainComponent getMainComponent() {
        return mainComponent;
    }
}