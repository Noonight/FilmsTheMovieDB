package com.example.admin.filmsthemoviedb.mvp.presenter

import com.example.admin.filmsthemoviedb.api.NetworkManager
import com.example.admin.filmsthemoviedb.api.model.MoviePopularResponse
import com.example.admin.filmsthemoviedb.mvp.base.BaseActivityView
import com.example.admin.filmsthemoviedb.mvp.base.BasePresenter
import com.example.admin.filmsthemoviedb.mvp.base.BaseView
import com.example.admin.filmsthemoviedb.mvp.model.MovieModel
import rx.Scheduler
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


class PopularMoviePresenter : BasePresenter {

    private var view: BaseActivityView? = null
    private var model: MovieModel? = MovieModel()

    private var mNetworkManager: NetworkManager? = null

    override fun attachView(baseView: BaseView) {
        view = baseView as BaseActivityView
    }

    override fun detachView() {
        view = null
    }

    override fun viewIsReady() {
        loadData()
    }

    private var mMoviePopularSubscription: Subscription? = null

    fun loadData() {
        /*if (model == null) {
            model = MovieModel()
        }*/
        if (mNetworkManager == null) {
            mNetworkManager = NetworkManager()
        }
        /*mMoviePopularSubscription = mNetworkManager!!.apiService
                .getPopularMovieByPage(1)
                .map(MoviePopularResponse::getResult)
                .doOnSubscribe(view::showProgress!!)
                .doAfterTerminate(view::hideProgress!!)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()*/
        /*view?.showProgress()
        view?.setData(model!!.getPopularMovieOnPage(1)!!)
        view?.hideProgress()*/
    }

}