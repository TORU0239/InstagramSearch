package io.toru.instagramsearch.di.module;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

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

@Singleton
@Module
public class MainModule {
    private static final String TAG = MainModule.class.getSimpleName();

    public MainModule() {}

    @Singleton
    @Provides
    public Retrofit injectRetrofit(){
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

//    @Singleton
//    @Provides
//    public ConnectionInstagram getConnectionInstagram(@Named("inject_retrofit") Retrofit retrofit){
//        return retrofit.create(ConnectionInstagram.class);
//    }
}
