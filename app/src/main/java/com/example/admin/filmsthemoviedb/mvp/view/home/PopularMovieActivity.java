package com.example.admin.filmsthemoviedb.mvp.view.home;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.admin.filmsthemoviedb.R;
import com.example.admin.filmsthemoviedb.api.model.MoviePopularResponse;
import com.example.admin.filmsthemoviedb.api.NetworkManager;
import com.example.admin.filmsthemoviedb.api.model.MoviePopularResponseBody;
import com.example.admin.filmsthemoviedb.common.Log;
import com.example.admin.filmsthemoviedb.mvp.base.BaseActivityView;
import com.example.admin.filmsthemoviedb.mvp.presenter.MoviePresenter;
import com.example.admin.filmsthemoviedb.mvp.presenter.PopularMoviePresenter;
import com.example.admin.filmsthemoviedb.mvp.view.movie.MovieActivity;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class PopularMovieActivity extends AppCompatActivity implements BaseActivityView {

    public static final String TAG = PopularMovieActivity.class.getName();

    @BindView(R.id.request_recycler)
    RecyclerView mRequestRecycler;

    @BindView(R.id.loading_view)
    FrameLayout mLoadingView;

    private MoviePresenter presenter;
    private ProgressDialog progress;
    private NetworkManager mNetworkManager;
    private PopularMovieAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ButterKnife.bind(this);

        init();
    }

    private void init() {
        mRequestRecycler = findViewById(R.id.request_recycler);
        //mRequestRecycler.setLayoutManager(new LinearLayoutManager(this));
        //StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        //LinearLayoutManager layoutManager = new LinearLayoutManager(this,
                //LinearLayoutManager.HORIZONTAL, false);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        mRequestRecycler.setLayoutManager(layoutManager);
        mNetworkManager = new NetworkManager();

        adapter = new PopularMovieAdapter();
        subscAdapter(adapter);
        mRequestRecycler.setAdapter(adapter);

        /*subscription = mNetworkManager.getApiService()
                .getPopularMovieByPage(1)
                .map(MoviePopularResponse::getResult)
                //.doOnSubscribe(this::showProgress)
                //.doOnTerminate(this::hideProgress)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::setData, throwable -> Log.d(throwable.getMessage()));*/

        initPresenter();
        //showData();
    }

    private void initPresenter() {
        presenter = new MoviePresenter();
        presenter.attachView(this);
        presenter.viewIsReady();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //subscription.unsubscribe();
    }
/* private void showData() {
        //if (!mNetworkManager.getApiKey().isEmpty()) {
            getData();
        //} else {
            //Toast.makeText(this, "Not found api_key", Toast.LENGTH_SHORT).show();
        //}
    }*/

    /*private void getData() {
        mNetworkManager.getApiService().getPopularMovieByPage(1).enqueue(new Callback<MoviePopularResponse>() {
            @Override
            public void onResponse(Call<MoviePopularResponse> call, Response<MoviePopularResponse> response) {
                adapter = new PopularMovieAdapter(response.body());
                mRequestRecycler.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<MoviePopularResponse> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }*/

    public void startMovieActivity(Bundle bundle) {
        Intent intent = new Intent(this, MovieActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    void subscAdapter(PopularMovieAdapter adater) {
        adater.subscribeActivity(this);
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {
        //progress = ProgressDialog.show(this, "", getString(R.string.please_wait));
        mRequestRecycler.setVisibility(View.GONE);
    }

    @Override
    public void hideProgress() {
        if (progress != null) {
            progress.dismiss();
        }
        mRequestRecycler.setVisibility(View.VISIBLE);
    }

    @Override
    public void setData(@NotNull ArrayList<MoviePopularResponseBody> data) {
        adapter.setData(data);
    }
}
