package com.example.admin.filmsthemoviedb.mvp.view.home;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.admin.filmsthemoviedb.R;
import com.example.admin.filmsthemoviedb.api.model.MoviePopularResponse;
import com.example.admin.filmsthemoviedb.api.model.MoviePopularResponseBody;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PopularMovieAdapter extends RecyclerView.Adapter<PopularMovieAdapter.ViewHolder> {

    private OnItemClickListener mOnItemClick;

    private final ArrayList<MoviePopularResponseBody> mMovies;

    public PopularMovieAdapter() {
        mMovies = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movies, parent, false);
        return new ViewHolder(view);
    }

    public static final String IMAGE_URL = "https://image.tmdb.org/t/p/w154";

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.bind(mMovies.get(position), mOnItemClick);
    }

    public void setData(ArrayList<MoviePopularResponseBody> newMovies) {
        mMovies.clear();
        mMovies.addAll(newMovies);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.list_item_poster)
        ImageView mIvListItemPoster;
        @BindView(R.id.list_item_title)
        TextView mEtListItemTitle;
        @BindView(R.id.movies_item_recycler)
        LinearLayout mItemView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(final MoviePopularResponseBody item, OnItemClickListener listener) {
            Glide.with(itemView)
                    .load( IMAGE_URL + item.getmPosterPath())
                    .into(mIvListItemPoster);
            mEtListItemTitle.setText(item.getmTitle());
            mItemView.setOnClickListener(view -> listener.onClickItem(item));
        }
    }

    public interface OnItemClickListener {
        void onClickItem(MoviePopularResponseBody item);
    }

    public void setmOnItemClick(OnItemClickListener mOnItemClick) {
        this.mOnItemClick = mOnItemClick;
    }
}
