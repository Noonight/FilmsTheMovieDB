package com.example.admin.filmsthemoviedb;


import android.app.Application;
import android.content.Context;

public class App extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        //Realm.init(this);
    }

    public static Context getContext() {
        return mContext;
    }
}
