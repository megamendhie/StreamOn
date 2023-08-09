package com.megamendhie.core.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Mendhie on 8/9/2023
 * Copyright (c) 2023 Mendhie Imeh. All rights reserved.
 */

data class MovieGenres(
    val genres: List<MovieGenre>
)

@Entity(tableName = "movie_genre_table")
data class MovieGenre(
    @PrimaryKey
    val id: Int,
    val name: String
)
