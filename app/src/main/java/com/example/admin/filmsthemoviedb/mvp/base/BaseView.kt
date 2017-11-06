package com.example.admin.filmsthemoviedb.mvp.base


interface BaseView {
    fun showProgress()
    fun hideProgress()
    fun showMessage(message: String)
}