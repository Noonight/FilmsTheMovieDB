package com.example.admin.filmsthemoviedb.mvp.model

import com.example.admin.filmsthemoviedb.api.NetworkManager
import com.example.admin.filmsthemoviedb.api.model.MoviePopularResponse
import com.example.admin.filmsthemoviedb.api.model.MovieResponse
import com.example.admin.filmsthemoviedb.common.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MovieModel(private val mNetworkManager: NetworkManager = NetworkManager()) {

    fun getPopularMovieOnPage(page: Int): MoviePopularResponse? {
        var popularMovie: MoviePopularResponse? = null
        mNetworkManager.apiService.getPopularMovieByPage(page).enqueue(object : Callback<MoviePopularResponse> {

            override fun onResponse(call: Call<MoviePopularResponse>?, response: Response<MoviePopularResponse>?) {
                popularMovie = response?.body()
            }

            override fun onFailure(call: Call<MoviePopularResponse>?, t: Throwable) {
                Log.d(t.message)
            }
        })
        return popularMovie
    }

    fun getMovieById(id: Long): MovieResponse? {
        var movie: MovieResponse? = null
        mNetworkManager.apiService.getMovieById(id).enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>?, response: Response<MovieResponse>?) {
                movie = response?.body()
            }

            override fun onFailure(call: Call<MovieResponse>?, t: Throwable) {
                Log.d(t.message)
            }
        })
        return movie
    }

}