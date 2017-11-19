package com.example.admin.filmsthemoviedb;

import android.content.Context;

import com.example.admin.filmsthemoviedb.api.ApiService;
import com.example.admin.filmsthemoviedb.api.NetworkManager;
import com.example.admin.filmsthemoviedb.common.Log;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppModule {
    private App mApplication;

    public AppModule(App mApplication) {
        this.mApplication = mApplication;
    }

    @Singleton
    @Provides
    public Context provideContext() {
        return mApplication.getApplicationContext();
    }

    @Singleton
    @Provides
    public Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .client(okHttpClient)
                .build();
    }

    @Singleton
    @Provides
    public OkHttpClient provideOkHttpClinet() {
        return new OkHttpClient().newBuilder().addNetworkInterceptor(chain -> {

            HttpUrl originalUrl = chain.request().url();

            HttpUrl url = originalUrl.newBuilder()
                    //.addQueryParameter("api_key", mSharedPreferences.getString("api_key", ""))
                    .addQueryParameter("api_key", NetworkManager.API_KEY)
                    //.addQueryParameter("page", mSharedPreferences.getString("page", "1"))
                    .build();
            Log.d(String.valueOf(url));
            Request.Builder requestBuilder = chain.request().newBuilder()
                    .url(url);
            Request request = requestBuilder.build();

            return chain.proceed(request);
        }).build();
    }

    @Singleton
    @Provides
    public ApiService provideApiService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }
}
