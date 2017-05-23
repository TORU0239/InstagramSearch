package io.toru.instagramsearch.detail.presenter;

import android.util.Log;

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
    private Retrofit retrofit;

    public DetailPresenterImpl(DetailTask.DetailView view) {
        this.view = view;
        initRetrofit();
    }

    private void initRetrofit(){
        if(retrofit == null){
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.addInterceptor(loggingInterceptor);

            retrofit = new Retrofit.Builder()
                    .baseUrl(Constant.INSTAGRAM_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();
        }
    }

    @Override
    public void onCallMoreList(String instagramId, String lastImageId) {
        view.onShowProgressDialog();
        ConnectionInstagram service = retrofit.create(ConnectionInstagram.class);
        Call<InstagramModel> callImagesFromLastImageId = service.getModelWithMaxId(instagramId, lastImageId);
        callImagesFromLastImageId.enqueue(new Callback<InstagramModel>() {
            @Override
            public void onResponse(Call<InstagramModel> call, Response<InstagramModel> response) {
                Log.w(TAG, response.message());
                if(response.isSuccessful()){
                    // 결과를 받아서 뷰를 업데이트해 준다
                    view.onHideProgressDialog();

                    try {
                        view.onUpdateInstagramList(response.body());
                    }
                    catch (NullPointerException e){
                        e.printStackTrace();
                        view.onShowToast("관련된 데이터가 없습니다.");
                    }
                }
                else{
                    Log.w(TAG, "failed! " + response.code());
                    view.onHideProgressDialog();
                    view.onShowToast("서버에서 응답을 받아오는 데 실패했습니다.");
                }
            }

            @Override
            public void onFailure(Call<InstagramModel> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}