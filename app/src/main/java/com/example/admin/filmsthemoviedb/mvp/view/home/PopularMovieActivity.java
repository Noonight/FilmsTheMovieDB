package com.example.admin.filmsthemoviedb.mvp.view.home;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.filmsthemoviedb.App;
import com.example.admin.filmsthemoviedb.R;
import com.example.admin.filmsthemoviedb.api.NetworkManager;
import com.example.admin.filmsthemoviedb.api.model.MoviePopularResponseBody;
import com.example.admin.filmsthemoviedb.common.Log;
import com.example.admin.filmsthemoviedb.common.loading.LoadingDialog;
import com.example.admin.filmsthemoviedb.common.loading.LoadingView;
import com.example.admin.filmsthemoviedb.mvp.presenter.PopularMovieActivityPresenter;
import com.example.admin.filmsthemoviedb.mvp.view.movie.MovieActivity;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PopularMovieActivity extends AppCompatActivity implements PopularMovieView {

    public static final String TAG = PopularMovieActivity.class.getName();

    @BindView(R.id.request_recycler)
    RecyclerView mRequestRecycler;

    @BindView(R.id.loading_view)
    FrameLayout mLoadingView;

    @BindView(R.id.error_message)
    TextView mTvErrorMessage;

    @Inject
    PopularMovieActivityPresenter presenter;

    private ProgressDialog progress;
    private NetworkManager mNetworkManager;
    private PopularMovieAdapter adapter;
    private LoadingView loadingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ButterKnife.bind(this);
        App.getComponent(this).inject(this);
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
            //Toast.makeText(PopularMovieActivity.this, "Title : " + item.getmTitle(), Toast.LENGTH_SHORT).show();
            openMovieActivity(item);
        });
        mRequestRecycler.setAdapter(adapter);

        loadingView = LoadingDialog.view(getSupportFragmentManager());

        initPresenter();
    }

    private void initPresenter() {
        presenter.attachView(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void showProgress() {
        loadingView.showLoadingDialog();
        //mLoadingView.setVisibility(View.VISIBLE);
        //mRequestRecycler.setVisibility(View.GONE);
    }

    @Override
    public void hideProgress() {
        loadingView.hideLoadingDialog();
        //mLoadingView.setVisibility(View.GONE);
        //mRequestRecycler.setVisibility(View.VISIBLE);
    }

    @Override
    public void setData(@NotNull List<MoviePopularResponseBody> data) {
        Log.d(data.toString());
        ArrayList<MoviePopularResponseBody> newData = new ArrayList<>();
        newData.addAll(data);
        Log.d(newData.toString());
        adapter.setData(newData);
    }

    private int curentPage = 1;

    @Override
    public int getPage() {
        return 1;
    }

    private void openMovieActivity(MoviePopularResponseBody item) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(MovieActivity.class.getCanonicalName(), item);

        Intent intent = new Intent(this, MovieActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void showMessage(@NotNull String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(@NotNull String error) {
        if (!TextUtils.isEmpty(error)) {
            mTvErrorMessage.setText(error);
        }
        loadingView.hideLoadingDialog();
        //mLoadingView.setVisibility(View.GONE);
        mTvErrorMessage.setVisibility(View.VISIBLE);
        //mRequestRecycler.setVisibility(View.GONE);
    }
}
