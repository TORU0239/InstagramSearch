package io.toru.instagramsearch.main;

import android.app.Activity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import io.toru.instagramsearch.R;
import io.toru.instagramsearch.base.view.BaseActivity;
import io.toru.instagramsearch.databinding.ActivityMainBinding;
import io.toru.instagramsearch.main.presenter.MainPresenterImpl;
import io.toru.instagramsearch.main.presenter.MainTask;

public class MainActivity extends BaseActivity implements MainTask.MainView{
    private static final String TAG = MainActivity.class.getSimpleName();

    private ActivityMainBinding mainBinding;
    private MainTask.MainPresenter presenter;

    @Override
    public Activity getCurrentActivity() {
        return MainActivity.this;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initInstances() {
        presenter = new MainPresenterImpl(this);
        mainBinding = (ActivityMainBinding)binding;
        mainBinding.toolbarMain.inflateMenu(R.menu.search_menu);
        final SearchView searchView = (SearchView)mainBinding.toolbarMain.getMenu()
                                    .findItem(R.id.action_search).getActionView();
        searchView.setQueryHint(getString(R.string.main_search_id));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.w(TAG, "query:" + query);
                searchView.clearFocus();
                //TODO: 여기서 검색을 태운다.
                presenter.onCallNetwork(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    @Override
    public void onShowToast(String str) {
        Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onShowProgressDialog() {
//        mainBinding.rlMainProgressbar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onHideProgressDialog() {
//        mainBinding.rlMainProgressbar.setVisibility(View.GONE);
    }

    @Override
    public void onUpdateInstagramList() {}
}