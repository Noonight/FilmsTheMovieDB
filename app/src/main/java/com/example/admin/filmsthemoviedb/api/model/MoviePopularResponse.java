package com.example.admin.filmsthemoviedb.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MoviePopularResponse {

    @SerializedName("page")
    private long mPage;
    @SerializedName("total_results")
    private long mTotalResults;
    @SerializedName("total_pages")
    private long mTotalPages;
    @SerializedName("results")
    private List<MoviePopularResponseBody> mResults;

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

    public List<MoviePopularResponseBody> getmResults() {
        return mResults;
    }

    public void setmResults(List<MoviePopularResponseBody> mResults) {
        this.mResults = mResults;
    }
}
