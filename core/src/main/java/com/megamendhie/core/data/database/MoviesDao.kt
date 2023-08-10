package com.megamendhie.core.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.megamendhie.core.data.models.*

/**
 * Created by Mendhie on 8/7/2023
 * Copyright (c) 2023 Mendhie Imeh. All rights reserved.
 */

@Dao
interface MoviesDao {
    @Insert(onConflict = REPLACE)
    fun insertFavoriteMovies(movies: List<FavoriteMovie>)

    @Insert(onConflict = REPLACE)
    fun insertFavoriteMovie(movie: FavoriteMovie)

    @Query("SELECT * FROM favorite_movies_table")
    fun getFavoriteMovies(): LiveData<List<FavoriteMovie>>

    @Query("DELETE FROM favorite_movies_table WHERE id = :movieId")
    fun deleteFavoriteMovie(movieId: Int)

    @Insert(onConflict = REPLACE)
    fun insertTrendingMovies(movies: List<TrendingMovie>)

    @Query("SELECT * FROM trending_movies_table")
    fun getTrendingMovies(): LiveData<List<TrendingMovie>>

    @Insert(onConflict = REPLACE)
    fun insertPopularMovies(movies: List<PopularMovie>)

    @Query("SELECT * FROM popular_movies_table")
    fun getPopularMovies(): LiveData<List<PopularMovie>>

    @Insert(onConflict = REPLACE)
    fun insertDiscoverMovies(movies: List<DiscoverMovie>)

    @Query("SELECT * FROM discover_movies_table")
    fun getDiscoverMovies(): LiveData<List<DiscoverMovie>>

    @Insert(onConflict = REPLACE)
    fun insertMovieGenres(movieGenres: List<MovieGenre>)

    @Query("SELECT * FROM movie_genre_table")
    fun getMovieGenres(): LiveData<List<MovieGenre>>

    @Insert(onConflict = REPLACE)
    fun insertMovieDetails(movieDetails: MovieDetails)

    @Query("SELECT * FROM movie_details_table WHERE id = :movieId")
    fun getMovieDetails(movieId: Int): LiveData<MovieDetails>
}