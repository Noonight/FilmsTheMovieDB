package com.example.admin.filmsthemoviedb.mvp.presenter

import com.example.admin.filmsthemoviedb.mvp.base.BaseActivityView
import com.example.admin.filmsthemoviedb.mvp.base.BasePresenter
import com.example.admin.filmsthemoviedb.mvp.base.BaseView


class HomePresenter : BasePresenter {

    private var view: BaseView? = null

    override fun attachView(baseView: BaseView) {
        view = baseView
    }

    override fun detachView() {
        view = null
    }

    override fun viewIsReady() {
        // Do something
    }

}