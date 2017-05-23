package io.toru.instagramsearch.base.view;

/**
 * Created by wonyoung on 2017. 5. 19..
 */

public interface BaseView {
    void onShowToast(String str);
    void onShowToast(int strId);
    void onShowProgressDialog();
    void onHideProgressDialog();
}