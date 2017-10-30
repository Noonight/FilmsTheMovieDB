package com.example.admin.filmsthemoviedb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.admin.filmsthemoviedb.api.MovieResponseBody;

public class MovieActivity extends AppCompatActivity {

    private MovieResponseBody movie;

    public void setMovie(MovieResponseBody movie) {
        this.movie = movie;
    }
    public static final MovieActivity newInstance(MovieResponseBody movie) {
        MovieActivity activity = new MovieActivity();
        activity.setMovie(movie);
        return activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
    }
}
