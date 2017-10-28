package com.example.admin.filmsthemoviedb.api;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Admin on 28.10.2017.
 */

public class MovieResponse {

    @SerializedName("page")
    private long mPage;
    @SerializedName("total_results")
    private long mTotalResults;
    @SerializedName("total_pages")
    private long mTotalPages;
    @SerializedName("results")
    private List<MovieResponseBody> mResults;

    public long getmPage() {
        return mPage;
    }

    public void setmPage(long mPage) {
        this.mPage = mPage;
    }

    public long getmTotalResults() {
        return mTotalResults;
    }

    public void setmTotalResults(long mTotalResults) {
        this.mTotalResults = mTotalResults;
    }

    public long getmTotalPages() {
        return mTotalPages;
    }

    public void setmTotalPages(long mTotalPages) {
        this.mTotalPages = mTotalPages;
    }

    public List<MovieResponseBody> getmResults() {
        return mResults;
    }

    public void setmResults(List<MovieResponseBody> mResults) {
        this.mResults = mResults;
    }
}
