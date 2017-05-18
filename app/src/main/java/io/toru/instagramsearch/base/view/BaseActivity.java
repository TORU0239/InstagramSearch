package io.toru.instagramsearch.base.view;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import io.toru.instagramsearch.R;

/**
 * Created by wonyoung on 2017. 5. 18..
 */

public abstract class BaseActivity extends AppCompatActivity {
    protected ViewDataBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(getCurrentActivity(), getLayoutId());
        setAppTaskDescription();
        initUI();
    }

    @TargetApi(21)
    protected void setAppTaskDescription() {
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP){
            Bitmap bm = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
            ActivityManager.TaskDescription taskDescription = new ActivityManager.TaskDescription(getString(R.string.app_name), bm,
                    ContextCompat.getColor(this, R.color.gelato_color_primary_dark));
            setTaskDescription(taskDescription);
        }
    }

    public abstract Activity getCurrentActivity();
    public abstract int getLayoutId();
    public abstract void initUI();
}