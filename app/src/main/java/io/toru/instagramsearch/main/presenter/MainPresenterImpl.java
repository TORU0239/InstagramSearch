package io.toru.instagramsearch.main.presenter;

import android.util.Log;

import io.toru.instagramsearch.main.model.InstagramModel;
import io.toru.instagramsearch.network.ConnectionInstagram;
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

    public MainPresenterImpl(MainTask.MainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void onCallNetwork(String id) {
        mainView.onShowProgressDialog();
        // TODO: 여기서 네트웍 콜을 한다.

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.instagram.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ConnectionInstagram service = retrofit.create(ConnectionInstagram.class);
        Call<InstagramModel> itemModelCall = service.getModel("toru_0239");
        itemModelCall.enqueue(new Callback<InstagramModel>() {
            @Override
            public void onResponse(Call<InstagramModel> call, Response<InstagramModel> response) {
                Log.w(TAG, response.message());
                Log.w(TAG, "itemList :: " + response.body().getItemList().length
                        + ", status: " + response.body().getStatus());

                // 결과를 받아서 뷰를 업데이트해 준다
                mainView.onUpdateInstagramList(response.body());
                mainView.onHideProgressDialog();
            }

            @Override
            public void onFailure(Call<InstagramModel> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
