package com.app.data.di

import com.app.data.repository.SatelliteDetailRepositoryImpl
import com.app.domain.repository.SatelliteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class SatelliteDetailRepositoryModule {

    @Binds
    abstract fun bindRepository(repository: SatelliteDetailRepositoryImpl): SatelliteRepository

}