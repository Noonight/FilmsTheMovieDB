package com.example.admin.filmsthemoviedb.api.model

import com.google.gson.annotations.SerializedName
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey


open class MovieResponse(
        @SerializedName("adult") var mAdult: Boolean = false,
        @SerializedName("backdrop_path") var mBackdropPath: String = "",
        //@SerializedName("belongs_to_collection") var mBelongsToCollection: Object = null,
        @SerializedName("budget") var mBudget: Long = -1,
        @SerializedName("genres") var mGenres: RealmList<MovieGenres>? = null,
        @SerializedName("homepage") var mHomePage: String = "",
        @PrimaryKey @SerializedName("id") var mId: Long = -1,
        @SerializedName("imdb_id") var mImdbId: String = "",
        @SerializedName("original_language") var mLanguage: String = "",
        @SerializedName("original_title") var mOriginalTitle: String = "",
        @SerializedName("overview") var mOverview: String = "",
        @SerializedName("popularity") var mPopularity: Double = -0.1,
        @SerializedName("poster_path") var mPosterPath: String = "",
        @SerializedName("production_companies") var mMovieProductionCompanies: RealmList<MovieProductionCompanies>? = null,
        @SerializedName("production_countries") var movieProductionCuntries: RealmList<MovieProductionCuntries>? = null,
        @SerializedName("release_date") var mReleaseDate: String = "",
        @SerializedName("revenue") var mRevenue: Long = -1,
        @SerializedName("runtime") var mRuntime: Long = -1,
        @SerializedName("spoken_language") var mMovieSpokenLanguage: RealmList<MovieSpokenLanguage>? = null,
        @SerializedName("status") var mStatus: String = "",
        @SerializedName("tagline") var mTagLine: String = "",
        @SerializedName("title") var mTitle: String = "",
        @SerializedName("video") var mVideo: Boolean = false,
        @SerializedName("vote_average") var mVoteAverage: Double = -0.1,
        @SerializedName("vote_count") var mVoteCount: Long = -1
) : RealmObject()

data class MovieSpokenLanguage(
        @SerializedName("iso_639_1") var mIso: String = "",
        @SerializedName("name") var mName: String = ""
) : RealmObject()

data class MovieCollection(
        @SerializedName("id") var mId: Long = -1,
        @SerializedName("name") var mName: String = "",
        @SerializedName("poster_path") var mPosterPath: String = "",
        @SerializedName("backdrop_path") var mBackdropPath: String = ""
) : RealmObject()

/*
* genres - жанры
* */
data class MovieGenres(
        @SerializedName("id") var mId: Long = -1,
        @SerializedName("name") var mName: String = ""
) : RealmObject()

data class MovieProductionCuntries(
        @SerializedName("iso_3166_1") var mIso: String = "",
        @SerializedName("name") var mName: String = ""
) : RealmObject()

data class MovieProductionCompanies(
        @SerializedName("name") var mName: String = "",
        @SerializedName("id") var mId: Long = -1
) : RealmObject()