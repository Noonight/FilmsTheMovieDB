package com.example.admin.filmsthemoviedb.dagger;

import android.content.Context;

import com.example.admin.filmsthemoviedb.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private Application mApplication;

    public AppModule(Application mApplication) {
        this.mApplication = mApplication;
    }

    @Singleton
    @Provides
    public Context provideContext() {
        return mApplication.getApplicationContext();
    }


}
