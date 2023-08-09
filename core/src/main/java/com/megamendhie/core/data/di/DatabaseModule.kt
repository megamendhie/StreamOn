package com.megamendhie.core.data.di

import android.app.Application
import androidx.room.Room
import com.megamendhie.core.data.database.MoviesDao
import com.megamendhie.core.data.database.StreamOnDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

/**
 * Created by Mendhie on 8/7/2023
 * Copyright (c) 2023 Mendhie Imeh. All rights reserved.
 */

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun providesDb(app: Application, callback: StreamOnDatabase.DbCallback): StreamOnDatabase{
        return Room.databaseBuilder(app, StreamOnDatabase::class.java, "streamon.db")
            .addCallback(callback)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun providesMoviesDao(db: StreamOnDatabase): MoviesDao = db.moviesDao()


    @Singleton
    @Provides
    fun providesCoroutineScope() = CoroutineScope(SupervisorJob())

}