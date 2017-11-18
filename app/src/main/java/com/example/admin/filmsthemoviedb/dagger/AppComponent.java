package com.example.admin.filmsthemoviedb.dagger;

import com.example.admin.filmsthemoviedb.mvp.view.home.PopularMovieActivity;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = AppModule.class)
@Singleton
public interface AppComponent {
    void inject(PopularMovieActivity activity);
}
