package com.example.admin.filmsthemoviedb.mvp.presenter;


import com.example.admin.filmsthemoviedb.api.NetworkManager;
import com.example.admin.filmsthemoviedb.api.model.MovieResponse;
import com.example.admin.filmsthemoviedb.common.Log;
import com.example.admin.filmsthemoviedb.mvp.base.BasePresenter;
import com.example.admin.filmsthemoviedb.mvp.view.movie.MovieView;

import io.realm.Realm;
import io.realm.RealmResults;
import rx.Observable;
import rx.Scheduler;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MovieActivityPresenter extends BasePresenter<MovieView> {

    private NetworkManager networkManager;
    private Subscription subscription;
    @Override
    protected void updateView() {
        loadData();
    }

    private void loadData() {
        if (networkManager == null) {
            networkManager = new NetworkManager();
        }
        int movieId = getView().getSerializeMovieId();
        subscription = networkManager.getApiService().getMovieById((long) movieId)
                .flatMap(movieResponse -> {
                    Realm.getDefaultInstance().executeTransaction(realm -> {
                        realm.delete(MovieResponse.class);
                        realm.insert(movieResponse);
                    });
                    return Observable.just(movieResponse);
                })
                .onErrorResumeNext(throwable -> {
                    Realm realm = Realm.getDefaultInstance();
                    MovieResponse defResponse = realm.where(MovieResponse.class).equalTo("mId", movieId).findFirst();
                    return Observable.just(defResponse);
                })
                .doOnSubscribe(getView()::showProgress)
                .doAfterTerminate(getView()::hideProgress)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getView()::bindMovie, throwable -> Log.d(throwable.getMessage()));
    }
}
