package com.example.admin.filmsthemoviedb.mvp.base


interface BasePresenterInterface {

    fun attachView(baseView: BaseView)

    fun detachView()

    fun viewIsReady()

}