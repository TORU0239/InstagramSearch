package io.toru.instagramsearch.main.presenter;

import dagger.Component;
import io.toru.instagramsearch.di.module.NetworkModule;

/**
 * Created by wonyoung on 2017. 5. 23..
 */

@Component(modules = NetworkModule.class)
public interface MainPresenterComponent {
    void injectMainPresenter(MainPresenterImpl presenter);
}
