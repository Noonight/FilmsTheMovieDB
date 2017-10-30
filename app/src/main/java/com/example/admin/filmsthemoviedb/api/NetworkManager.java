package com.example.admin.filmsthemoviedb.api;

import android.content.SharedPreferences;

import com.example.admin.filmsthemoviedb.common.Log;
import com.google.gson.Gson;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkManager {

    public static final String API_KEY = "6ef8fac92d0f608902ec99ae9ca02f5b";

    private OkHttpClient mOkHttpClient;
    private ApiService mApiService;
    private SharedPreferences mSharedPreferences;

    public NetworkManager(/*Context context*/) {
        //mSharedPreferences = context.getSharedPreferences("Settings", Context.MODE_PRIVATE);

        setApiKey(API_KEY);

        mOkHttpClient = new OkHttpClient().newBuilder().addNetworkInterceptor(chain -> {

            HttpUrl originalUrl = chain.request().url();

            HttpUrl url = originalUrl.newBuilder()
                    .addQueryParameter("api_key", mSharedPreferences.getString("api_key", ""))
                    //.addQueryParameter("page", mSharedPreferences.getString("page", "1"))
                    .build();
            Log.d(String.valueOf(url));
            Request.Builder requestBuilder = chain.request().newBuilder()
                    .url(url);
            Request request = requestBuilder.build();

            return chain.proceed(request);
        }).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .client(mOkHttpClient)
                .build();
        mApiService = retrofit.create(ApiService.class);
    }

    private void setApiKey(String apiKey) {
        mSharedPreferences.edit().putString("api_key", apiKey).apply();
    }

    /*public String getApiKey() {
        return mSharedPreferences.getString("api_key", "");
    }*/

    public ApiService getApiService() {
        return mApiService;
    }
}
