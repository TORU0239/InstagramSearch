package io.toru.instagramsearch.detail.presenter;

import dagger.Component;
import io.toru.instagramsearch.di.module.NetworkModule;

/**
 * Created by wonyoung on 2017. 5. 24..
 */

@Component(modules = NetworkModule.class)
public interface DetailPresenterComponent {
    void injectDetailPresenter(DetailPresenterImpl presenter);
}