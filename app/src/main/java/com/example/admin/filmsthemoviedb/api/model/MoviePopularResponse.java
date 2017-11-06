package com.example.admin.filmsthemoviedb.api.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class MoviePopularResponse extends RealmObject implements Serializable {

    @PrimaryKey
    @SerializedName("page")
    private long mPage;
    @SerializedName("total_results")
    private long mTotalResults;
    @SerializedName("total_pages")
    private long mTotalPages;
    @SerializedName("results")
    private RealmList<MoviePopularResponseBody> mResults;

    public List<MoviePopularResponseBody> getResult() {
        if (mResults == null)  {
            return new ArrayList<>();
        }
        return mResults;
    }

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

    public void setmResults(RealmList<MoviePopularResponseBody> mResults) {
        this.mResults = mResults;
    }
}
