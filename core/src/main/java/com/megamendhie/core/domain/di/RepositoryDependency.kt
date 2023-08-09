package com.megamendhie.core.domain.di

import com.megamendhie.core.domain.repositories.MoviesRepository
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Created by Mendhie on 8/8/2023
 * Copyright (c) 2023 Mendhie Imeh. All rights reserved.
 */

@EntryPoint
@InstallIn(SingletonComponent::class)
interface RepositoryDependency {
    fun providesMoviesRepository(): MoviesRepository
}