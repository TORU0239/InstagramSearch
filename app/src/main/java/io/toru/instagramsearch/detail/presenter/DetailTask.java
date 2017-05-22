package io.toru.instagramsearch.detail.presenter;

import io.toru.instagramsearch.base.listener.OnInfiniteScrollListener;
import io.toru.instagramsearch.base.view.BaseView;
import io.toru.instagramsearch.main.model.InstagramModel;

/**
 * Created by wonyoung on 2017. 5. 22..
 */

public interface DetailTask {
    interface DetailView extends BaseView, OnInfiniteScrollListener{
        void onUpdateInstagramList(InstagramModel instagramModel);
    }

    interface DetailPresenter{
        void onCallMoreList(String instagramId, String lastImageId);
    }
}