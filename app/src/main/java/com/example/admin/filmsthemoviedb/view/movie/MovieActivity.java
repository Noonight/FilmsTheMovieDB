package com.example.admin.filmsthemoviedb.view.movie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.admin.filmsthemoviedb.R;
import com.example.admin.filmsthemoviedb.api.NetworkManager;
import com.example.admin.filmsthemoviedb.api.model.MoviePopularResponseBody;
import com.example.admin.filmsthemoviedb.api.model.MovieResponse;
import com.example.admin.filmsthemoviedb.common.Log;
import com.example.admin.filmsthemoviedb.view.home.HomeMovieAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieActivity extends AppCompatActivity {

    @BindView(R.id.movie_title_tv)
    TextView mEtMovieTitle;
    @BindView(R.id.movie_post_iv)
    ImageView mIvMoviePost;

    private MoviePopularResponseBody movie;
    private NetworkManager mNetworkManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        init();
        bindView();
    }

    private void init() {
        movie = (MoviePopularResponseBody) getIntent().getSerializableExtra(this.getClass().getCanonicalName());
        mNetworkManager = new NetworkManager(this);
    }

    private void bindView() {
        ButterKnife.bind(this);

        getData();
    }

    private void getData() {
        Log.d(String.valueOf(movie.getmId()));
        Toast.makeText(this, "" + movie.getmId(), Toast.LENGTH_SHORT).show();
        mNetworkManager.getApiService().getMovieById((long) movie.getmId()).enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                mEtMovieTitle.setText(response.body().getMTitle());
                Glide.with(mIvMoviePost)
                        .load(HomeMovieAdapter.IMAGE_URL + response.body().getMPosterPath())
                        .into(mIvMoviePost);
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.d(t.getMessage());
            }
        });
    }
}
