package com.megamendhie.core.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.megamendhie.core.data.converters.Converters
import kotlinx.parcelize.Parcelize

/**
 * Created by Mendhie on 8/7/2023
 * Copyright (c) 2023 Mendhie Imeh. All rights reserved.
 */

@Parcelize
@Entity(tableName = "trending_movies_table")
@TypeConverters(Converters::class)
data class TrendingMovie(
    val adult: Boolean,
    @SerializedName("backdrop_path")
    val backdropPath: String,
    @SerializedName("genre_ids")
    val genreIds: List<Int>,
    @PrimaryKey
    val id: Int,
    @SerializedName("original_title")
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("release_date")
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("vote_count")
    val voteCount: Long
) : Parcelable
