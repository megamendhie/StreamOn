package com.megamendhie.streamon.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import com.megamendhie.core.data.models.AddFavorite
import com.megamendhie.core.data.models.FavoriteMovie
import com.megamendhie.core.domain.repositories.MoviesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Mendhie on 8/8/2023
 * Copyright (c) 2023 Mendhie. All rights reserved.
 */
class MoviesViewModel @Inject constructor(private val moviesRepo: MoviesRepository){
    private val viewModelJob = SupervisorJob()
    private val scope = CoroutineScope(Dispatchers.Main + viewModelJob)

    val favoriteMovies = moviesRepo.moviesDao.getFavoriteMovies()
    val trendingMovies = moviesRepo.moviesDao.getTrendingMovies()
    val popularMovies = moviesRepo.moviesDao.getPopularMovies()
    val discoverMovies = moviesRepo.moviesDao.getDiscoverMovies()

    val addFavoriteMovieResponse: MutableLiveData<Int> = moviesRepo.addFavoriteMovieResponse

    fun getDiscoveredMovies(token: String) = scope.launch {
        //generate random number between 1 and 20 for discover movies
        val page = (1..10).random()
        moviesRepo.getDiscoveredMovies(token, page)
    }

    fun getTrendingMovies(token: String, page: Int = 1) = scope.launch {
        moviesRepo.getTrendingMovies(token, page)
    }

    fun getPopularMovies(token: String, page: Int = 1) = scope.launch {
        moviesRepo.getPopularMovies(token, page)
    }

    fun getFavoriteMovies(token: String, page: Int = 1) = scope.launch {
        moviesRepo.getFavoriteMovies(token, page)
    }

    fun addFavorite(token: String, favorite: FavoriteMovie, add: Boolean) = scope.launch {
        moviesRepo.addFavoriteMovie(token, favorite, add)
    }

    fun clearVal() = moviesRepo.clearValue()


}