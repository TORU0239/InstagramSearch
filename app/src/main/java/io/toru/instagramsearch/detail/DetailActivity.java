package io.toru.instagramsearch.detail;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import io.toru.instagramsearch.R;
import io.toru.instagramsearch.base.view.BaseActivity;
import io.toru.instagramsearch.databinding.ActivityDetailBinding;
import io.toru.instagramsearch.main.model.InstagramItemModel;
import io.toru.instagramsearch.main.model.InstagramModel;

/**
 * Created by wonyoung on 2017. 5. 22..
 */

public class DetailActivity extends BaseActivity {
    private static final String TAG = DetailActivity.class.getSimpleName();

    private ActivityDetailBinding activityDetailBinding;


    public static Intent getDetailActivityIntent(Context ctx, InstagramModel model){
        return new Intent(ctx, DetailActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP)
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
        InstagramModel model = getIntent().getParcelableExtra("total_model");

        Log.w(TAG, "model count:: " + model.getStatus());
        Log.w(TAG, "model count:: " + model.isMoreAvailable());
        Log.w(TAG, "model count:: " + model.getItemList().length);

        if(model != null){
            activityDetailBinding.rcvDetail.setLayoutManager(new LinearLayoutManager(getCurrentActivity(), LinearLayoutManager.HORIZONTAL, false));
            activityDetailBinding.rcvDetail.setAdapter(new DetailAdapter(model));
        }
    }
}