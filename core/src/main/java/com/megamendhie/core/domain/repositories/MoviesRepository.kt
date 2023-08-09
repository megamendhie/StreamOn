package com.megamendhie.core.domain.repositories

import android.util.Log
import com.megamendhie.core.data.database.MoviesDao
import com.megamendhie.core.data.models.*
import com.megamendhie.core.data.remote.TheMovieDbApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by Mendhie on 8/8/2023
 * Copyright (c) 2023 Mendhie Imeh. All rights reserved.
 */
class MoviesRepository @Inject constructor(val moviesDao: MoviesDao, private val scope: CoroutineScope){
    private val TAG = "MoviesRepository"
    private val includeAdult = "false"
    private val includeVideo = "false"
    private val language = "en-US"

    fun getDiscoveredMovies(token: String, page: Int) {
        val sortBy = "popularity.desc"
        TheMovieDbApi.tmdbApi.getDiscoverMovies(
            token, includeAdult, includeVideo, language, page.toString(), sortBy
        ).enqueue(object:
            Callback<DiscoverMovieResponse> {
            override fun onResponse(
                call: Call<DiscoverMovieResponse>,
                response: Response<DiscoverMovieResponse>
            ) {
                Log.d(TAG, "onResponse getDiscoveredMovies: $response")
                if (response.isSuccessful){
                    scope.launch {
                        val movies = response.body()?.results ?: listOf()
                        moviesDao.insertDiscoverMovies(movies)
                    }
                }
            }

            override fun onFailure(call: Call<DiscoverMovieResponse>, t: Throwable) {
                Log.d(TAG, "onFailure getDiscoveredMovies: $t")
            }
        })
    }

    fun getPopularMovies(token: String, page: Int){
        TheMovieDbApi.tmdbApi.getPopularMovies(token, language, page.toString())
            .enqueue(object : Callback<PopularMovieResponse>{
            override fun onResponse(
                call: Call<PopularMovieResponse>,
                response: Response<PopularMovieResponse>
            ) {
                Log.d(TAG, "onResponse: getPopularMovies $response")
                if(response.isSuccessful){
                    scope.launch {
                        val movies = response.body()?.results ?: listOf()
                        moviesDao.insertPopularMovies(movies)
                    }
                }
            }

            override fun onFailure(call: Call<PopularMovieResponse>, t: Throwable) {
                Log.d(TAG, "onFailure: getPopularMovies $t")
            }
        })
    }

    fun getFavoriteMovies(token: String, page: Int) {
        val sortBy = "created_at.asc"
        TheMovieDbApi.tmdbApi.getFavoriteMovies(
            token, language, page.toString(), sortBy
        ).enqueue(object : Callback<TrendingMovieResponse>{
            override fun onResponse(
                call: Call<TrendingMovieResponse>,
                response: Response<TrendingMovieResponse>
            ) {
                Log.d(TAG, "onResponse getFavoriteMovies: $response")
            }

            override fun onFailure(call: Call<TrendingMovieResponse>, t: Throwable) {
                Log.d(TAG, "onFailure getFavoriteMovies: $t")
            }
        })
    }

    fun getTrendingMovies(token: String, page: Int) {
        TheMovieDbApi.tmdbApi.getTrendingMovies(
            token, page.toString()
        ).enqueue(object : Callback<TrendingMovieResponse> {
            override fun onResponse(
                call: Call<TrendingMovieResponse>,
                response: Response<TrendingMovieResponse>
            ) {
                Log.d(TAG, "onResponse getTrendingMovies: $response")
                if(response.isSuccessful){
                    scope.launch {
                        val movies = response.body()?.results ?: listOf()
                        moviesDao.insertTrendingMovies(movies)
                    }
                }
            }

            override fun onFailure(call: Call<TrendingMovieResponse>, t: Throwable) {
                Log.d(TAG, "onFailure getTrendingMovies: $t")
            }

        })
    }

    fun addFavoriteMovie(token: String, favorite: AddFavorite) {
        TheMovieDbApi.tmdbApi.addFavoriteMovie(
            token, "application/json", favorite
        ).enqueue(object : Callback<AddFavoriteResponse> {
            override fun onResponse(
                call: Call<AddFavoriteResponse>,
                response: Response<AddFavoriteResponse>
            ) {
                Log.d(TAG, "onResponse addFavoriteMovie: $response")
            }

            override fun onFailure(call: Call<AddFavoriteResponse>, t: Throwable) {
                Log.d(TAG, "onFailure addFavoriteMovie: $t")
            }
        })
    }

    fun getMovieDetails(token: String, movie_id: Int, type: String = "trending"){
        TheMovieDbApi.tmdbApi.getMovieDetails(
            token, movie_id, language
        ).enqueue(object : Callback<TrendingMovie>{
            override fun onResponse(call: Call<TrendingMovie>, response: Response<TrendingMovie>) {
                Log.d(TAG, "onResponse getMovieDetails: $response")
                if(response.isSuccessful){

                }
            }

            override fun onFailure(call: Call<TrendingMovie>, t: Throwable) {
                Log.d(TAG, "onFailure getMovieDetails: $t")
            }

        })
    }

    fun getMovieGenres(token: String){
        TheMovieDbApi.tmdbApi.getMovieGenres(
            token, language
        ).enqueue(object: Callback<MovieGenres>{
            override fun onResponse(call: Call<MovieGenres>, response: Response<MovieGenres>) {
                Log.d(TAG, "onResponse getMovieGenres: $response")
                if(response.isSuccessful){
                    scope.launch {
                        val genres = response.body()?.genres ?: listOf()
                        moviesDao.insertMovieGenres(genres)
                    }
                }
            }

            override fun onFailure(call: Call<MovieGenres>, t: Throwable) {
                Log.d(TAG, "onFailure getMovieGenres: $t")
            }

        })
    }
}