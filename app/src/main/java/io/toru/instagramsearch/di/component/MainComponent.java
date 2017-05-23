package io.toru.instagramsearch.di.component;

import javax.inject.Singleton;

import dagger.Component;
import io.toru.instagramsearch.di.module.NetworkModule;
import io.toru.instagramsearch.network.ConnectionInstagram;

/**
 * Created by wonyoung on 2017. 5. 23..
 */

@Singleton
@Component(modules = NetworkModule.class)
public interface MainComponent {
    ConnectionInstagram getConectionInstagram();
}
