package com.example.admin.filmsthemoviedb.mvp.view.movie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.admin.filmsthemoviedb.R;
import com.example.admin.filmsthemoviedb.api.model.MoviePopularResponseBody;
import com.example.admin.filmsthemoviedb.api.model.MovieResponse;
import com.example.admin.filmsthemoviedb.mvp.presenter.MovieActivityPresenter;
import com.example.admin.filmsthemoviedb.mvp.view.home.PopularMovieAdapter;

import org.jetbrains.annotations.NotNull;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieActivity extends AppCompatActivity implements MovieView{

    @BindView(R.id.movie_detail)
    LinearLayout mMovieDetail;
    @BindView(R.id.movie_title_tv)
    TextView mEtMovieTitle;
    @BindView(R.id.movie_post_iv)
    ImageView mIvMoviePost;

    private MovieActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        init();
    }

    private void init() {
        ButterKnife.bind(this);
        initPresenter();
    }

    private void initPresenter() {
        presenter = new MovieActivityPresenter();
        presenter.attachView(this);
    }

    @Override
    public int getSerializeMovieId() {
        MoviePopularResponseBody movie = (MoviePopularResponseBody) getIntent().getSerializableExtra(this.getClass().getCanonicalName());
        return movie.getmId();
    }

    @Override
    public void showProgress() {
        mMovieDetail.setVisibility(View.GONE);
    }

    @Override
    public void hideProgress() {
        mMovieDetail.setVisibility(View.VISIBLE);
    }

    @Override
    public void showMessage(@NotNull String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void bindMovie(MovieResponse movie) {
        mEtMovieTitle.setText(movie.getMTitle());
        Glide.with(mIvMoviePost)
                .load(PopularMovieAdapter.IMAGE_URL + movie.getMPosterPath())
                .into(mIvMoviePost);
    }
}
