package com.example.admin.filmsthemoviedb.mvp.base

import com.example.admin.filmsthemoviedb.api.model.MoviePopularResponse
import com.example.admin.filmsthemoviedb.api.model.MoviePopularResponseBody


interface BaseActivityView : BaseLoadingView {

    fun showToast(message: String)

    fun setData(data: ArrayList<MoviePopularResponseBody>)
}