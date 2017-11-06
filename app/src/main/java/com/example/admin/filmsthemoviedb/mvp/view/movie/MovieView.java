package com.example.admin.filmsthemoviedb.mvp.view.movie;


import com.example.admin.filmsthemoviedb.api.model.MovieResponse;
import com.example.admin.filmsthemoviedb.mvp.base.BaseView;

public interface MovieView extends BaseView{
    void bindMovie(MovieResponse movie);
    int getSerializeMovieId();
}
