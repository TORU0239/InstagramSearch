package io.toru.instagramsearch.main.presenter;

import io.toru.instagramsearch.base.view.BaseView;
import io.toru.instagramsearch.main.model.InstagramModel;

/**
 * Created by wonyoung on 2017. 5. 19..
 */

public interface MainTask {
    interface MainView extends BaseView{
        void onUpdateInstagramList(InstagramModel instagramModel);
    }

    interface MainPresenter{
        void onCallNetwork(String id);
    }
}
