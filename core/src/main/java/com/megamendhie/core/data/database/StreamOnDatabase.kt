package com.megamendhie.core.data.database

import android.util.Log
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.megamendhie.core.data.converters.Converters
import com.megamendhie.core.data.models.*
import javax.inject.Inject
import javax.inject.Provider

/**
 * Created by Mendhie on 8/7/2023
 * Copyright (c) 2023 Mendhie Imeh. All rights reserved.
 */

@Database(
    entities = [MovieDetails::class, TrendingMovie::class, PopularMovie::class,
        DiscoverMovie::class, MovieGenre::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class StreamOnDatabase: RoomDatabase() {

    abstract fun moviesDao(): MoviesDao

    class DbCallback @Inject constructor(private val db: Provider<StreamOnDatabase>): Callback(){
        private val TAG = "StreamOnDatabase"

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            Log.d(TAG, "onCreate: db created - $db")
        }
    }
}