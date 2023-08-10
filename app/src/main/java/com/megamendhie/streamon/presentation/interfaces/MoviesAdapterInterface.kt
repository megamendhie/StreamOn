package com.megamendhie.streamon.presentation.interfaces

import com.megamendhie.core.data.models.FavoriteMovie

/**
 * Created by Mendhie on 8/10/2023
 * Copyright (c) 2023 Mendhie Imeh. All rights reserved.
 */

interface MoviesAdapterInterface {
    fun favIconClick(movie: FavoriteMovie)
}