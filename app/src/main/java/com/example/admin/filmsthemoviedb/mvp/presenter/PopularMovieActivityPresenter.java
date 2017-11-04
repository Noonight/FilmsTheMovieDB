package com.example.admin.filmsthemoviedb.mvp.presenter;


import com.example.admin.filmsthemoviedb.api.NetworkManager;
import com.example.admin.filmsthemoviedb.api.model.MoviePopularResponse;
import com.example.admin.filmsthemoviedb.common.Log;
import com.example.admin.filmsthemoviedb.mvp.base.BaseActivityView;
import com.example.admin.filmsthemoviedb.mvp.base.BasePresenter;
import com.example.admin.filmsthemoviedb.mvp.base.BasePresenterInterface;
import com.example.admin.filmsthemoviedb.mvp.base.BaseView;

import org.jetbrains.annotations.NotNull;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class PopularMovieActivityPresenter extends BasePresenter<BaseActivityView> {
    private NetworkManager mNetworkManager;

    @Override
    protected void updateView() {
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
                .doOnSubscribe(getView()::showProgress)
                .doAfterTerminate(getView()::hideProgress)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getView()::setData, throwable -> Log.d(throwable.getMessage()));
    }
}
