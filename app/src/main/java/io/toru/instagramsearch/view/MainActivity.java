package io.toru.instagramsearch.view;

import android.app.Activity;

import io.toru.instagramsearch.R;
import io.toru.instagramsearch.base.view.BaseActivity;
import io.toru.instagramsearch.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity {
    private ActivityMainBinding mainBinding;

    @Override
    public Activity getCurrentActivity() {
        return MainActivity.this;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initUI() {
        mainBinding = (ActivityMainBinding)binding;
    }
}