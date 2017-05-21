package io.toru.instagramsearch.main.presenter;

/**
 * Created by wonyoung on 2017. 5. 19..
 */

public class MainPresenterImpl implements MainTask.MainPresenter {
    private static final String TAG = MainPresenterImpl.class.getSimpleName();

    private MainTask.MainView mainView;

    public MainPresenterImpl(MainTask.MainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void onCallNetwork(String id) {
        mainView.onShowProgressDialog();
        // TODO: 여기서 네트웍 콜을 한다.

        // 결과를 받아서 뷰를 업데이트해 준다
        mainView.onUpdateInstagramList();
        mainView.onHideProgressDialog();
    }
}
