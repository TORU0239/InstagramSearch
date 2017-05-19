package io.toru.instagramsearch.main;

import android.app.Activity;
import android.support.v7.widget.SearchView;
import android.util.Log;

import io.toru.instagramsearch.R;
import io.toru.instagramsearch.base.view.BaseActivity;
import io.toru.instagramsearch.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

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
        mainBinding.toolbarMain.inflateMenu(R.menu.search_menu);

        SearchView searchView = (SearchView)mainBinding.toolbarMain.getMenu()
                                    .findItem(R.id.action_search).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.w(TAG, "query:" + query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.w(TAG, "newText:" + newText);
                return true;
            }
        });
    }
}