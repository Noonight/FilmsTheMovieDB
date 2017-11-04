package com.example.admin.filmsthemoviedb.mvp.view.home;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.admin.filmsthemoviedb.R;
import com.example.admin.filmsthemoviedb.api.NetworkManager;
import com.example.admin.filmsthemoviedb.api.model.MoviePopularResponseBody;
import com.example.admin.filmsthemoviedb.mvp.base.BaseActivityView;
import com.example.admin.filmsthemoviedb.mvp.presenter.PopularMovieActivityPresenter;
import com.example.admin.filmsthemoviedb.mvp.view.movie.MovieActivity;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PopularMovieActivity extends AppCompatActivity implements BaseActivityView {

    public static final String TAG = PopularMovieActivity.class.getName();

    @BindView(R.id.request_recycler)
    RecyclerView mRequestRecycler;

    @BindView(R.id.loading_view)
    FrameLayout mLoadingView;

    private PopularMovieActivityPresenter presenter;
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
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        //GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        mRequestRecycler.setLayoutManager(layoutManager);
        mNetworkManager = new NetworkManager();

        adapter = new PopularMovieAdapter();
        adapter.setmOnItemClick(item -> {
            Toast.makeText(PopularMovieActivity.this, "Title : " + item.getmTitle(), Toast.LENGTH_SHORT).show();
            openMovieActivity(item);
        });
        mRequestRecycler.setAdapter(adapter);

        initPresenter();
    }

    private void initPresenter() {
        presenter = new PopularMovieActivityPresenter();
        presenter.attachView(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //subscription.unsubscribe();
    }

    public void startMovieActivity(Bundle bundle) {
        Intent intent = new Intent(this, MovieActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {
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

    private void openMovieActivity(MoviePopularResponseBody item) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(MovieActivity.class.getCanonicalName(), item);

        Intent intent = new Intent(this, MovieActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
