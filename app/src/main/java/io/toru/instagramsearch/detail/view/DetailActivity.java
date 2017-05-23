package io.toru.instagramsearch.detail.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import io.toru.instagramsearch.BuildConfig;
import io.toru.instagramsearch.R;
import io.toru.instagramsearch.base.view.BaseActivity;
import io.toru.instagramsearch.databinding.ActivityDetailBinding;
import io.toru.instagramsearch.detail.presenter.DetailPresenterImpl;
import io.toru.instagramsearch.detail.presenter.DetailTask;
import io.toru.instagramsearch.main.model.InstagramItemModel;
import io.toru.instagramsearch.main.model.InstagramModel;
import io.toru.instagramsearch.util.Constant;

/**
 * Created by wonyoung on 2017. 5. 22..
 */

public class DetailActivity extends BaseActivity implements DetailTask.DetailView{
    private static final String TAG = DetailActivity.class.getSimpleName();

    private ActivityDetailBinding activityDetailBinding;
    private DetailTask.DetailPresenter presenter;

    private String searchedId;
    private boolean isMoreAvailable;

    private InstagramModel model;
    private ArrayList<InstagramItemModel> itemModelList;
    private DetailAdapter adapter;

    public static Intent getDetailActivityIntent(Context ctx, String searchedId, ArrayList<InstagramItemModel> modelList,
                                                 boolean isMoreAvailable, String modelId){
        return new Intent(ctx, DetailActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP)
                .putExtra(Constant.KEY_MODEL_SEARCHED_ID, searchedId)
                .putExtra(Constant.KEY_MODEL_LIST, modelList)
                .putExtra(Constant.KEY_MODEL_IS_MORE_AVAILABLE, isMoreAvailable)
                .putExtra(Constant.KEY_MODEL_CURRENT_ITEM_ID, modelId);

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

        searchedId = getIntent().getStringExtra(Constant.KEY_MODEL_SEARCHED_ID);
        itemModelList = getIntent().getParcelableArrayListExtra(Constant.KEY_MODEL_LIST);
        isMoreAvailable = getIntent().getBooleanExtra(Constant.KEY_MODEL_IS_MORE_AVAILABLE, true);
        String id = getIntent().getStringExtra(Constant.KEY_MODEL_CURRENT_ITEM_ID);
        adapter = new DetailAdapter(itemModelList, this);

        activityDetailBinding.rcvDetail.setLayoutManager(new LinearLayoutManager(getCurrentActivity(), LinearLayoutManager.HORIZONTAL, false));
        activityDetailBinding.rcvDetail.setAdapter(adapter);
        activityDetailBinding.rcvDetail.getLayoutManager().scrollToPosition(getCurrentPosition(id));
    }

    private int getCurrentPosition(String id){
        int position = 0;
        for(InstagramItemModel model : itemModelList){
            if(model.getId().equals(id)){
                break;
            }
            position++;
        }
        return position;
    }

    @Override
    public void onShowToast(String str) {
        Toast.makeText(getCurrentActivity(), str, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onShowToast(int strId) {
        Toast.makeText(getCurrentActivity(), strId, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onShowProgressDialog() {
        activityDetailBinding.rlProgressbar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onHideProgressDialog() {
        activityDetailBinding.rlProgressbar.setVisibility(View.GONE);
    }

    @Override
    public void onLoadMore(String lastImageId) {
        if(BuildConfig.DEBUG){
            Log.w(TAG, "last image id: " + lastImageId);
        }

        if(model == null){
            if(isMoreAvailable){
                presenter.onCallMoreList(searchedId, lastImageId);
            }
        }
        else if(model.isMoreAvailable()){
            presenter.onCallMoreList(searchedId, lastImageId);
        }
    }

    // 얘는 Load More 시에만 호출된다.
    @Override
    public void onUpdateInstagramList(InstagramModel instagramModel) {
        model = instagramModel;
        adapter.setInstagramModel(instagramModel);
    }
}