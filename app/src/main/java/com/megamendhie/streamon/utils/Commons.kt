package com.megamendhie.streamon.utils

/**
 * Created by Mendhie on 8/8/2023
 * Copyright (c) 2023 Mendhie Imeh. All rights reserved.
 */

class Commons {
    companion object{
        const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w500"
        const val TRENDING_MOVIES = "trending_movies"
        const val POPULAR_MOVIES = "popular_movies"

        fun popularityScore(score: Double): Int {
            return (score * 10).toInt()
        }
    }
}