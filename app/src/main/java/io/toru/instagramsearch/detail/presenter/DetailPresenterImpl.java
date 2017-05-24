package io.toru.instagramsearch.detail.presenter;

import android.util.Log;

import javax.inject.Inject;

import io.toru.instagramsearch.R;
import io.toru.instagramsearch.di.module.NetworkModule;
import io.toru.instagramsearch.main.model.InstagramModel;
import io.toru.instagramsearch.network.ConnectionInstagram;
import io.toru.instagramsearch.util.Constant;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by wonyoung on 2017. 5. 22..
 */

public class DetailPresenterImpl implements DetailTask.DetailPresenter {
    private static final String TAG = DetailPresenterImpl.class.getSimpleName();
    private DetailTask.DetailView view;

    @Inject
    ConnectionInstagram instagramService;

    public DetailPresenterImpl(DetailTask.DetailView view) {
        this.view = view;
        DaggerDetailPresenterComponent.builder().networkModule(new NetworkModule()).build().injectDetailPresenter(this);
    }

    @Override
    public void onCallMoreList(String instagramId, String lastImageId) {
        view.onShowProgressDialog();
        Call<InstagramModel> callImagesFromLastImageId = instagramService.getModelWithMaxId(instagramId, lastImageId);
        callImagesFromLastImageId.enqueue(new Callback<InstagramModel>() {
            @Override
            public void onResponse(Call<InstagramModel> call, Response<InstagramModel> response) {
                Log.w(TAG, response.message());
                if(response.isSuccessful()){
                    // 결과를 받아서 뷰를 업데이트해 준다
                    view.onHideProgressDialog();

                    try {
                        if(response.body().getItemList().length > 0){
                            view.onUpdateInstagramList(response.body());
                        }
                        else{
                            view.onShowToast(R.string.no_data);
                        }
                    }
                    catch (NullPointerException e){
                        e.printStackTrace();
                        view.onShowToast(R.string.no_data);
                    }
                }
                else{
                    Log.w(TAG, "failed! " + response.code());
                    view.onHideProgressDialog();
                    view.onShowToast(R.string.failed_to_server_response);
                }
            }

            @Override
            public void onFailure(Call<InstagramModel> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}