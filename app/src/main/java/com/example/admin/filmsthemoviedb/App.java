package com.example.admin.filmsthemoviedb;


import android.app.Application;
import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.rx.RealmObservableFactory;

public class App extends Application {

    private static App mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        initializeRealm();
    }

    private void initializeRealm() {
        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .rxFactory(new RealmObservableFactory())
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }

    public static App getAppContext() {
        return mInstance;
    }
}
