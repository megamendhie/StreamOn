package com.megamendhie.core.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


/**
 * Created by Mendhie on 8/9/2023
 * Copyright (c) 2023 Mendhie Imeh. All rights reserved.
 */

@Parcelize
data class Movie(
    val adult: Boolean,
    val backdropPath: String,
    val genreIds: List<Int>,
    val id: Int,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val voteAverage: Double
) : Parcelable
