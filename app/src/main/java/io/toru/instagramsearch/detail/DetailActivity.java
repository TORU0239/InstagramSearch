package io.toru.instagramsearch.detail;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.bumptech.glide.Glide;

import io.toru.instagramsearch.R;
import io.toru.instagramsearch.base.view.BaseActivity;
import io.toru.instagramsearch.databinding.ActivityDetailBinding;
import io.toru.instagramsearch.main.model.InstagramItemModel;

/**
 * Created by wonyoung on 2017. 5. 22..
 */

public class DetailActivity extends BaseActivity {
    private static final String TAG = DetailActivity.class.getSimpleName();

    private ActivityDetailBinding activityDetailBinding;

    public static Intent getDetailActivityIntent(Context ctx, InstagramItemModel model){
        return new Intent(ctx, DetailActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP)
                .putExtra("model", model);
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
        InstagramItemModel model = getIntent().getParcelableExtra("model");
        activityDetailBinding = (ActivityDetailBinding) binding;

        // test

        Log.w(TAG, "id::" + model.getId());

        Glide.with(getCurrentActivity())
                .load(model.getImages().getStandardResolution().getUrl())
                .into(activityDetailBinding.imgDetail);
        // test end

    }
}