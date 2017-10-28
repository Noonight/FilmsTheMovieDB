package com.example.admin.filmsthemoviedb.api;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Admin on 28.10.2017.
 */

public class NetworkManager {

    private OkHttpClient mOkHttpClient;
    private ApiService mApiService;
    private SharedPreferences mSharedPreferences;

    public NetworkManager(Context context) {
        mSharedPreferences = context.getSharedPreferences("Settings", Context.MODE_PRIVATE);

        mOkHttpClient = new OkHttpClient().newBuilder().addNetworkInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                HttpUrl originalUrl = chain.request().url();

                HttpUrl url = originalUrl.newBuilder()
                        .addQueryParameter("api_key", mSharedPreferences.getString("api_key", ""))
                        .build();
                Request.Builder requestBuilder = chain.request().newBuilder()
                        .url(url);
                Request request = requestBuilder.build();

                return chain.proceed(request);
            }
        }).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .client(mOkHttpClient)
                .build();
        mApiService = retrofit.create(ApiService.class);
    }

    public void setApiKey(String apiKey) {
        mSharedPreferences.edit().putString("api_key", apiKey).apply();
    }

    public String getApiKey() {
        return mSharedPreferences.getString("api_key", "");
    }

    public ApiService getApiService() {
        return mApiService;
    }
}
