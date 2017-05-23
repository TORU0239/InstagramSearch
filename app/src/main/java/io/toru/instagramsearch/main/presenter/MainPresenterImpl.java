package io.toru.instagramsearch.main.presenter;

import android.util.Log;

import javax.inject.Inject;

import io.toru.instagramsearch.R;
import io.toru.instagramsearch.app.InstagramSearchApplication;
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
 * Created by wonyoung on 2017. 5. 19..
 */

public class MainPresenterImpl implements MainTask.MainPresenter {
    private static final String TAG = MainPresenterImpl.class.getSimpleName();
    private MainTask.MainView mainView;

    @Inject
    ConnectionInstagram instagramService;

    public MainPresenterImpl(MainTask.MainView mainView) {
        this.mainView = mainView;
        MainPresenterComponent component = DaggerMainPresenterComponent.builder().networkModule(new NetworkModule()).build();
        component.injectMainPresenter(this);
    }

    @Override
    public void onCallNetwork(String id) {
        mainView.onShowProgressDialog();

        Call<InstagramModel> itemModelCall = instagramService.getModel(id);
        itemModelCall.enqueue(new Callback<InstagramModel>() {
            @Override
            public void onResponse(Call<InstagramModel> call, Response<InstagramModel> response) {
                Log.w(TAG, response.message());
                if(response.isSuccessful()){
                    Log.w(TAG, "success");
                    Log.w(TAG, "itemList :: " + response.body().getItemList().length
                            + ", status: " + response.body().getStatus());
                    // 결과를 받아서 뷰를 업데이트해 준다
                    mainView.onHideProgressDialog();

                    try {
                        if(response.body().getItemList().length > 0){
                            mainView.onUpdateInstagramList(response.body(), true);
                        }
                        else{
                            mainView.onShowToast(R.string.no_data);
                        }
                    }
                    catch (NullPointerException e){
                        e.printStackTrace();
                        mainView.onShowToast(R.string.no_data);
                    }
                }
                else{
                    Log.w(TAG, "failed! " + response.code());
                    mainView.onHideProgressDialog();
                    mainView.onShowToast(R.string.failed_to_server_response);
                }
            }

            @Override
            public void onFailure(Call<InstagramModel> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public void onCallMoreList(String instagramId, String lastImageId) {
        mainView.onShowProgressDialog();
        
        Call<InstagramModel> callImagesFromLastImageId = instagramService.getModelWithMaxId(instagramId, lastImageId);
        callImagesFromLastImageId.enqueue(new Callback<InstagramModel>() {
            @Override
            public void onResponse(Call<InstagramModel> call, Response<InstagramModel> response) {
                Log.w(TAG, response.message());
                if(response.isSuccessful()){
                    Log.w(TAG, "success");
                    Log.w(TAG, "itemList :: " + response.body().getItemList().length
                            + ", status: " + response.body().getStatus());
                    // 결과를 받아서 뷰를 업데이트해 준다
                    mainView.onHideProgressDialog();

                    try {
                        mainView.onUpdateInstagramList(response.body(), false);
                    }
                    catch (NullPointerException e){
                        e.printStackTrace();
                        mainView.onShowToast(R.string.no_data);
                    }
                }
                else{
                    Log.w(TAG, "failed! " + response.code());
                    mainView.onHideProgressDialog();
                    mainView.onShowToast(R.string.failed_to_server_response);
                }
            }

            @Override
            public void onFailure(Call<InstagramModel> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
