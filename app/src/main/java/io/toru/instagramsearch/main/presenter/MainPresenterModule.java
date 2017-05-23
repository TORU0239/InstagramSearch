package io.toru.instagramsearch.main.presenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by wonyoung on 2017. 5. 23..
 */

@Module
public class MainPresenterModule {
    private MainPresenterImpl presenter;

    public MainPresenterModule(MainPresenterImpl presenter) {
        this.presenter = presenter;
    }

    @Provides
    public MainPresenterImpl presenter(){
        return presenter;
    }
}
