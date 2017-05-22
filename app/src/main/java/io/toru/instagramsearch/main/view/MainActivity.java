package io.toru.instagramsearch.main.view;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import io.toru.instagramsearch.R;
import io.toru.instagramsearch.base.view.BaseActivity;
import io.toru.instagramsearch.databinding.ActivityMainBinding;
import io.toru.instagramsearch.main.model.InstagramItemModel;
import io.toru.instagramsearch.main.model.InstagramModel;
import io.toru.instagramsearch.main.presenter.MainPresenterImpl;
import io.toru.instagramsearch.main.presenter.MainTask;
import io.toru.instagramsearch.util.Util;

public class MainActivity extends BaseActivity implements MainTask.MainView{
    private static final String TAG = MainActivity.class.getSimpleName();

    private ActivityMainBinding mainBinding;
    private MainTask.MainPresenter presenter;

    private String instagramQuery;
    private InstagramModel instagramModel;

    private MainAdapter mainAdapter;

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
                instagramQuery = query;
                searchView.clearFocus();
                instagramModel = null;
                presenter.onCallNetwork(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        mainBinding.rcvMain.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        mainBinding.rcvMain.setHasFixedSize(false);
        mainAdapter = new MainAdapter(this);
        mainBinding.rcvMain.setAdapter(mainAdapter);
    }

    @Override
    public void onShowToast(String str) {
        Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onShowProgressDialog() {
        mainBinding.rlMainProgressbar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onHideProgressDialog() {
        mainBinding.rlMainProgressbar.setVisibility(View.GONE);
    }

    @Override
    public void onUpdateInstagramList(InstagramModel model, boolean clear){
        instagramModel = model;
        mainAdapter.setInstagramModel(instagramQuery, model, clear);
        mainAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoadMore(String lastImageId) {
        Log.w(TAG, "onLoadMore, id:: " + lastImageId);
        if(instagramModel.isMoreAvailable()){
            presenter.onCallMoreList(instagramQuery, lastImageId);
        }
    }
}