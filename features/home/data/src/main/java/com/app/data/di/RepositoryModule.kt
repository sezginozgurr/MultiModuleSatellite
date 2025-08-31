package com.app.data.di

import com.app.data.repository.SatelliteRepositoryImpl
import com.app.domain.repository.SatelliteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {

    @Binds
    abstract fun bindRepository(repository: SatelliteRepositoryImpl): SatelliteRepository
}