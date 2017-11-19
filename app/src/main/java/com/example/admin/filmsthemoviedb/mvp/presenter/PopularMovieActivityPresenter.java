package com.example.admin.filmsthemoviedb.mvp.presenter;


import com.example.admin.filmsthemoviedb.api.ApiService;
import com.example.admin.filmsthemoviedb.api.NetworkManager;
import com.example.admin.filmsthemoviedb.api.model.MoviePopularResponse;
import com.example.admin.filmsthemoviedb.api.model.MoviePopularResponseBody;
import com.example.admin.filmsthemoviedb.common.Log;
import com.example.admin.filmsthemoviedb.mvp.base.BasePresenter;
import com.example.admin.filmsthemoviedb.mvp.view.home.PopularMovieView;

import java.util.ArrayList;

import javax.inject.Inject;

import io.realm.Realm;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class PopularMovieActivityPresenter extends BasePresenter<PopularMovieView> {

    private NetworkManager mNetworkManager;

    private ApiService mApiService;

    @Inject
    public PopularMovieActivityPresenter(ApiService mApiService) {
        this.mApiService = mApiService;
    }

    @Override
    protected void updateView() {
        loadData();
    }

    private Subscription mMovieSubscription;

    private void loadData() {
        if (mNetworkManager == null)
            mNetworkManager = new NetworkManager();
        int page = getView().getPage();
        mMovieSubscription = mNetworkManager.getApiService()
                .getPopularMovieByPage(page)
                .flatMap(moviePopularResponse -> {
                    Realm.getDefaultInstance().executeTransaction(realm -> {
                        realm.delete(MoviePopularResponse.class);
                        realm.insert(moviePopularResponse);
                    });
                    return Observable.just(moviePopularResponse);
                })
                .onErrorResumeNext(throwable -> {
                    Realm realm = Realm.getDefaultInstance();
                    MoviePopularResponse defResponse = realm.where(MoviePopularResponse.class).findFirst();
                    return Observable.just(realm.copyFromRealm(defResponse));
                })
                .map(MoviePopularResponse::getResult)
                .doOnSubscribe(getView()::showProgress)
                .doAfterTerminate(getView()::hideProgress)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getView()::setData, throwable -> getView().showError(throwable.getMessage()));
    }
}
