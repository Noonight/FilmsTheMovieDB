package com.example.admin.filmsthemoviedb.api;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.text.DateFormat;
import java.util.List;

/**
 * Created by Admin on 28.10.2017.
 */

public class MovieResponseBody implements Parcelable {
    @SerializedName("vote_count")
    private long mVoteCount;
    @SerializedName("id")
    private long mId;
    @SerializedName("video")
    private boolean mVideo;
    @SerializedName("vote_average")
    private double mVoteAverage;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("popularity")
    private double mPopilarity;
    @SerializedName("poster_path")
    private String mPosterPath;
    @SerializedName("original_language")
    private String mOriginalLanguage;
    @SerializedName("original_title")
    private String mOriginalTitle;
    @SerializedName("genre_ids")
    private List<Long> mGenreIds;
    @SerializedName("backdrop_path")
    private String mBackdropPath;
    @SerializedName("adult")
    private boolean mAdult;
    @SerializedName("overview")
    private String mOverview;
    @SerializedName("release_date")
    private String mReleaseDate;

    protected MovieResponseBody(Parcel in) {
        mVoteCount = in.readLong();
        mId = in.readLong();
        mVideo = in.readByte() != 0;
        mVoteAverage = in.readDouble();
        mTitle = in.readString();
        mPopilarity = in.readDouble();
        mPosterPath = in.readString();
        mOriginalLanguage = in.readString();
        mOriginalTitle = in.readString();
        mBackdropPath = in.readString();
        mAdult = in.readByte() != 0;
        mOverview = in.readString();
        mReleaseDate = in.readString();
    }

    public static final Creator<MovieResponseBody> CREATOR = new Creator<MovieResponseBody>() {
        @Override
        public MovieResponseBody createFromParcel(Parcel in) {
            return new MovieResponseBody(in);
        }

        @Override
        public MovieResponseBody[] newArray(int size) {
            return new MovieResponseBody[size];
        }
    };

    public long getmVoteCount() {
        return mVoteCount;
    }

    public void setmVoteCount(long mVoteCount) {
        this.mVoteCount = mVoteCount;
    }

    public long getmId() {
        return mId;
    }

    public void setmId(long mId) {
        this.mId = mId;
    }

    public boolean ismVideo() {
        return mVideo;
    }

    public void setmVideo(boolean mVideo) {
        this.mVideo = mVideo;
    }

    public double getmVoteAverage() {
        return mVoteAverage;
    }

    public void setmVoteAverage(double mVoteAverage) {
        this.mVoteAverage = mVoteAverage;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public double getmPopilarity() {
        return mPopilarity;
    }

    public void setmPopilarity(double mPopilarity) {
        this.mPopilarity = mPopilarity;
    }

    public String getmPosterPath() {
        return mPosterPath;
    }

    public void setmPosterPath(String mPosterPath) {
        this.mPosterPath = mPosterPath;
    }

    public String getmOriginalLanguage() {
        return mOriginalLanguage;
    }

    public void setmOriginalLanguage(String mOriginalLanguage) {
        this.mOriginalLanguage = mOriginalLanguage;
    }

    public String getmOriginalTitle() {
        return mOriginalTitle;
    }

    public void setmOriginalTitle(String mOriginalTitle) {
        this.mOriginalTitle = mOriginalTitle;
    }

    public List<Long> getmGenreIds() {
        return mGenreIds;
    }

    public void setmGenreIds(List<Long> mGenreIds) {
        this.mGenreIds = mGenreIds;
    }

    public String getmBackdropPath() {
        return mBackdropPath;
    }

    public void setmBackdropPath(String mBackdropPath) {
        this.mBackdropPath = mBackdropPath;
    }

    public boolean ismAdult() {
        return mAdult;
    }

    public void setmAdult(boolean mAdult) {
        this.mAdult = mAdult;
    }

    public String getmOverview() {
        return mOverview;
    }

    public void setmOverview(String mOverview) {
        this.mOverview = mOverview;
    }

    public String getmReleaseDate() {
        return mReleaseDate;
    }

    public void setmReleaseDate(String mReleaseDate) {
        this.mReleaseDate = mReleaseDate;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }
}
