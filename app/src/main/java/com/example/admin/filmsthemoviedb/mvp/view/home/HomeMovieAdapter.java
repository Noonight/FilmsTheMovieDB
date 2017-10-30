package com.example.admin.filmsthemoviedb.mvp.view.home;


import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.admin.filmsthemoviedb.R;
import com.example.admin.filmsthemoviedb.api.model.MoviePopularResponse;
import com.example.admin.filmsthemoviedb.mvp.view.movie.MovieActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeMovieAdapter extends RecyclerView.Adapter<HomeMovieAdapter.ViewHolder> {

    private HomeActivity activity;
    private MoviePopularResponse mMovie;

    public HomeMovieAdapter(MoviePopularResponse body) {
        mMovie = body;
    }

    public void subscribeActivity(HomeActivity activity) {
        this.activity = activity;
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

            Bundle bundle = new Bundle();
            bundle.putSerializable(MovieActivity.class.getCanonicalName(), mMovie.getmResults().get(position));

            activity.startMovieActivity(bundle);
        });
    }

    @Override
    public int getItemCount() {
        return mMovie.getmResults().size();
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
