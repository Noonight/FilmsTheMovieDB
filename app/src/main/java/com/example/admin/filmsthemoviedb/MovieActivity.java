package com.example.admin.filmsthemoviedb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.example.admin.filmsthemoviedb.api.MovieResponseBody;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieActivity extends AppCompatActivity {

    @BindView(R.id.movie_title_tv)
    TextView mEtMovieTitle;

    private MovieResponseBody movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        init();
        bindView();
    }

    private void init() {
        movie = getIntent().getParcelableExtra(MovieResponseBody.class.getCanonicalName());
    }

    private void bindView() {
        ButterKnife.bind(this);

        if (!movie.getmTitle().isEmpty()) {
            mEtMovieTitle.setText(movie.getmTitle());
        }
    }
}
