package io.toru.instagramsearch.di.module;

import dagger.Module;
import dagger.Provides;
import io.toru.instagramsearch.network.ConnectionInstagram;
import io.toru.instagramsearch.util.Constant;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by wonyoung on 2017. 5. 23..
 */

@Module
public class NetworkModule {

    @Provides
    public Retrofit retrofit(){
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(loggingInterceptor);

        return new Retrofit.Builder()
                .baseUrl(Constant.INSTAGRAM_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
    }

    @Provides
    public ConnectionInstagram connectionInstagram(Retrofit retrofit){
        return retrofit.create(ConnectionInstagram.class);
    }
}
