package io.toru.instagramsearch.detail.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import io.toru.instagramsearch.R;
import io.toru.instagramsearch.base.view.BaseActivity;
import io.toru.instagramsearch.databinding.ActivityDetailBinding;
import io.toru.instagramsearch.detail.presenter.DetailPresenterImpl;
import io.toru.instagramsearch.detail.presenter.DetailTask;
import io.toru.instagramsearch.main.model.InstagramModel;

/**
 * Created by wonyoung on 2017. 5. 22..
 */

public class DetailActivity extends BaseActivity implements DetailTask.DetailView{
    private static final String TAG = DetailActivity.class.getSimpleName();

    private ActivityDetailBinding activityDetailBinding;
    private DetailTask.DetailPresenter presenter;

    private String searchedId;
    private InstagramModel model;
    private DetailAdapter adapter;


    public static Intent getDetailActivityIntent(Context ctx, String searchedId, InstagramModel model){
        return new Intent(ctx, DetailActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP)
                .putExtra("searched_id", searchedId)
                .putExtra("total_model", model);
    }

    @Override
    public Activity getCurrentActivity() {
        return DetailActivity.this;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_detail;
    }

    @Override
    public void initInstances() {
        activityDetailBinding = (ActivityDetailBinding) binding;
        presenter = new DetailPresenterImpl(this);

        searchedId = getIntent().getStringExtra("searched_id");
        model = getIntent().getParcelableExtra("total_model");
        adapter = new DetailAdapter(model);

        if(model != null){
            activityDetailBinding.rcvDetail.setLayoutManager(new LinearLayoutManager(getCurrentActivity(), LinearLayoutManager.HORIZONTAL, false));
            activityDetailBinding.rcvDetail.setAdapter(adapter);
        }
    }

    @Override
    public void onShowToast(String str) {
        Toast.makeText(getCurrentActivity(), str, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onShowProgressDialog() {}

    @Override
    public void onHideProgressDialog() {}

    @Override
    public void onLoadMore(String lastImageId) {
        if(model.isMoreAvailable()){
            presenter.onCallMoreList(searchedId, lastImageId);
        }
    }

    @Override
    public void onUpdateInstagramList(InstagramModel instagramModel) {

    }
}