package com.example.admin.filmsthemoviedb.api.model

import com.google.gson.annotations.SerializedName


data class MovieResponse(
        @SerializedName("adult") val mAdult: Boolean,
        @SerializedName("backdrop_path") val mBackdropPath: String,
        @SerializedName("belongs_to_collection") val mBelongsToCollection: MovieCollection,
        @SerializedName("budget") val mBudget: Long,
        @SerializedName("genres") val mGenres: List<MovieGenres>,
        @SerializedName("homepage") val mHomePage: String,
        @SerializedName("id") val mId: Long,
        @SerializedName("imdb_id") val mImdbId: String,
        @SerializedName("original_language") val mLanguage: String,
        @SerializedName("original_title") val mOriginalTitle: String,
        @SerializedName("overview") val mOverview: String,
        @SerializedName("popularity") val mPopularity: Double,
        @SerializedName("poster_path") val mPosterPath: String,
        @SerializedName("production_companies") val mMovieProductionCompanies: List<MovieProductionCompanies>,
        @SerializedName("production_countries") val movieProductionCuntries: List<MovieProductionCuntries>,
        @SerializedName("release_date") val mReleaseDate: String,
        @SerializedName("revenue") val mRevenue: Long,
        @SerializedName("runtime") val mRuntime: Long,
        @SerializedName("spoken_language") val mMovieSpokenLanguage: List<MovieSpokenLanguage>,
        @SerializedName("status") val mStatus: String,
        @SerializedName("tagline") val mTagLine: String,
        @SerializedName("title") var mTitle: String,
        @SerializedName("video") val mVideo: Boolean,
        @SerializedName("vote_average") val mVoteAverage: Double,
        @SerializedName("vote_count") val mVoteCount: Long
)

data class MovieSpokenLanguage(
        @SerializedName("iso_639_1") val mIso: String,
        @SerializedName("name") val mName: String
)

data class MovieCollection(
        @SerializedName("id") val mId: Long,
        @SerializedName("name") val mName: String,
        @SerializedName("poster_path") val mPosterPath: String,
        @SerializedName("backdrop_path") val mBackdropPath: String
)

/*
* genres - жанры
* */
data class MovieGenres(
        @SerializedName("id") val mId: Long,
        @SerializedName("name") val mName: String
)

data class MovieProductionCuntries(
        @SerializedName("iso_3166_1") val mIso: String,
        @SerializedName("name") val mName: String
)

data class MovieProductionCompanies(
        @SerializedName("name") val mName: String,
        @SerializedName("id") val mId: Long
)