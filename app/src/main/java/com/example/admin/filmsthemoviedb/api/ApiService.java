package com.example.admin.filmsthemoviedb.api;

import com.example.admin.filmsthemoviedb.api.model.MoviePopularResponse;
import com.example.admin.filmsthemoviedb.api.model.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface ApiService {

    @GET("movie/popular")
    Observable<MoviePopularResponse> getPopularMovieByPage(@Query("page") int page);

    @GET("movie/{id}")
    Observable<MovieResponse> getMovieById(@Path("id") Long id);

}
