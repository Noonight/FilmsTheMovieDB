package com.example.admin.filmsthemoviedb;


import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.admin.filmsthemoviedb.api.MovieResponse;
import com.example.admin.filmsthemoviedb.api.MovieResponseBody;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private MovieResponse mMovie;

    public MovieAdapter(MovieResponse body) {
        mMovie = body;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movies, parent, false);
        return new ViewHolder(view);
    }

    public static final String IMAGE_URL = "https://image.tmdb.org/t/p/w500";

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide
                .with(holder.itemView)
                .load( IMAGE_URL + mMovie.getmResults().get(position).getmPosterPath())
                .into(holder.mIvListItemPoster);
        holder.mEtListItemTitle.setText(mMovie.getmResults().get(position).getmTitle());
        holder.itemView.setOnClickListener(view -> {
            Log.d("DEBUG", "click on " + position);
        });
    }

    @Override
    public int getItemCount() {
        return mMovie.getmResults().size();
    }

    private void openMovieActivity(MovieResponseBody responseBody) {
        Bundle args = new Bundle();

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.list_item_poster)
        ImageView mIvListItemPoster;
        @BindView(R.id.list_item_title)
        TextView mEtListItemTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
