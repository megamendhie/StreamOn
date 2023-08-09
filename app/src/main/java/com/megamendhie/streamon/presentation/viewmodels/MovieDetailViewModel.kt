package com.megamendhie.streamon.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.megamendhie.core.data.models.Movie
import com.megamendhie.core.data.models.MovieDetails
import com.megamendhie.core.data.models.TrendingMovie
import com.megamendhie.core.domain.repositories.MoviesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Mendhie on 8/9/2023
 * Copyright (c) 2023 Mendhie Imeh. All rights reserved.
 */
class MovieDetailViewModel @Inject constructor(private val moviesRepo: MoviesRepository): ViewModel() {
    private val viewModelJob = SupervisorJob()
    private val scope = CoroutineScope(Dispatchers.Main + viewModelJob)

    val trendingMovie: MutableLiveData<Movie> = MutableLiveData()

    fun getMovieDetails(token : String, id: Int): LiveData<MovieDetails> {
        scope.launch {
            moviesRepo.getMovieDetails(token, id)
        }
        return moviesRepo.moviesDao.getMovieDetails(id)
    }

}