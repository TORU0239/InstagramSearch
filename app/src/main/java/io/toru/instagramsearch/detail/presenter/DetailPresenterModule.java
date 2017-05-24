package io.toru.instagramsearch.detail.presenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by wonyoung on 2017. 5. 24..
 */

@Module
public class DetailPresenterModule {
    public DetailPresenterModule(DetailPresenterImpl presenter) {
        this.presenter = presenter;
    }

    private DetailPresenterImpl presenter;

    @Provides
    public DetailPresenterImpl detailPresenter(){
        return presenter;
    }
}
