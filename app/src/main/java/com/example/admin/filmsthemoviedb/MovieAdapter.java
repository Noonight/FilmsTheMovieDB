package com.example.admin.filmsthemoviedb;


import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

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

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mEtListItemTitle.setOnClickListener(view -> {

        });
        holder.mEtListItemTitle.setText(mMovie.getmResults().get(position).getmTitle());
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
