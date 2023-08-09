package com.megamendhie.core.data.models

/**
 * Created by Mendhie on 8/7/2023
 * Copyright (c) 2023 Mendhie Imeh. All rights reserved.
 */
data class TrendingMovieResponse(
    val page: Int,
    val results: List<TrendingMovie>,
    val total_pages: Int,
    val total_results: Int
    )

data class PopularMovieResponse(
    val page: Int,
    val results: List<PopularMovie>,
    val total_pages: Int,
    val total_results: Int
)

data class DiscoverMovieResponse(
    val page: Int,
    val results: List<DiscoverMovie>,
    val total_pages: Int,
    val total_results: Int
)


