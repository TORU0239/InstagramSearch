package io.toru.instagramsearch.network;

import io.toru.instagramsearch.main.model.InstagramModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by wonyoung on 2017. 5. 21..
 */

public interface ConnectionInstagram {
    @GET("/{id}/media/?max_id=0")
    Call<InstagramModel> getModel(@Path("id") String id);
}