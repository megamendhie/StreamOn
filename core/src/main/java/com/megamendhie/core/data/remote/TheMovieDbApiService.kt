package com.megamendhie.core.data.remote

import com.megamendhie.core.data.models.*
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

/**
 * Created by Mendhie on 8/8/2023
 * Copyright (c) 2023 Mendhie Imeh. All rights reserved.
 */

const val BASE_URL = "https://api.themoviedb.org/3/"

const val movieGenre = "genre/movie/list"
const val discoverMovies = "discover/movie"
const val trendingMovies = "trending/movie/day"
const val popularMovies = "movie/popular"
const val addFavoriteMovie = "account/20247142/favorite"
const val getFavoriteMovies = "account/20247142/favorite/movies"

val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()


interface TheMovieDbApiService {

    @GET(discoverMovies)
    fun getDiscoverMovies(
        @Header("Authorization") token: String,
        @Query("include_adult") includeAdult: String,
        @Query("include_video") includeVideo: String,
        @Query("language") language: String,
        @Query("page") page: String,
        @Query("sort_by") sortBy: String,
    ): Call<DiscoverMovieResponse>

    @GET(trendingMovies)
    fun getTrendingMovies(
        @Header("Authorization") token: String,
        @Query("language") language: String
    ): Call<TrendingMovieResponse>


    @GET(popularMovies)
    fun getPopularMovies(
        @Header("Authorization") token: String,
        @Query("language") language: String,
        @Query("page") page: String
    ): Call<PopularMovieResponse>

    @GET(getFavoriteMovies)
    fun getFavoriteMovies(
        @Header("Authorization") token: String,
        @Query("language") language: String,
        @Query("page") page: String,
        @Query("sort_by") sortBy: String
    ): Call<TrendingMovieResponse>

    @POST(addFavoriteMovie)
    fun addFavoriteMovie(
        @Header ("Authorization") token: String,
        @Header("Content-Type") contentType: String,
        @Body addFavorite: AddFavorite
    ): Call<AddFavoriteResponse>

    @GET("movie/{movie_id}")
    fun getMovieDetails(
        @Header("Authorization") token: String,
        @Path("movie_id") movie_id: Int,
        @Query("language") language: String
    ): Call<TrendingMovie>

    @GET(movieGenre)
    fun getMovieGenres(
        @Header("Authorization") token: String,
        @Query("language") language: String
    ): Call<MovieGenres>
}

object TheMovieDbApi {
    val tmdbApi: TheMovieDbApiService by lazy {
        retrofit.create(TheMovieDbApiService::class.java)
    }
}