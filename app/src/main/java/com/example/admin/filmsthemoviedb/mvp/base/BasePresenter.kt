package com.example.admin.filmsthemoviedb.mvp.base


interface BasePresenter {

    fun attachView(baseView: BaseView)

    fun detachView()

    fun viewIsReady()

}