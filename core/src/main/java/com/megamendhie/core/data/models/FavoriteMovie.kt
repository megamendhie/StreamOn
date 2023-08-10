package com.megamendhie.core.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Mendhie on 8/10/2023
 * Copyright (c) 2023 Mendhie Imeh. All rights reserved.
 */

@Entity(tableName = "favorite_movies_table")
data class FavoriteMovie(
    @PrimaryKey
    val id: Int,
    val title: String
)
