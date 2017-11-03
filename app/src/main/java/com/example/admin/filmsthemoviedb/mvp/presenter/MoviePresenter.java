package com.example.admin.filmsthemoviedb.mvp.presenter;


import com.example.admin.filmsthemoviedb.api.NetworkManager;
import com.example.admin.filmsthemoviedb.api.model.MoviePopularResponse;
import com.example.admin.filmsthemoviedb.common.Log;
import com.example.admin.filmsthemoviedb.mvp.base.BaseActivityView;
import com.example.admin.filmsthemoviedb.mvp.base.BasePresenter;
import com.example.admin.filmsthemoviedb.mvp.base.BaseView;

import org.jetbrains.annotations.NotNull;

import rx.Observer;
import rx.Scheduler;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MoviePresenter implements BasePresenter{
    private BaseActivityView view;
    private NetworkManager mNetworkManager;

    @Override
    public void attachView(@NotNull BaseView baseView) {
        view = (BaseActivityView) baseView;
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public void viewIsReady() {
        loadData();
    }

    private Subscription mMovieSubscription;

    private void loadData() {
        if (mNetworkManager == null)
            mNetworkManager = new NetworkManager();
        //mNetworkManager = new NetworkManager();
        mMovieSubscription = mNetworkManager.getApiService()
                .getPopularMovieByPage(1)
                .map(MoviePopularResponse::getResult)
                .doOnSubscribe(view::showProgress)
                .doAfterTerminate(view::hideProgress)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(view::setData, throwable -> Log.d(throwable.getMessage()));
    }
}
