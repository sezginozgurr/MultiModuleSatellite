package com.app.data.di

import com.app.data.service.SatelliteService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    @Provides
    @Singleton
    fun provideCategoryApi(retrofit: Retrofit): SatelliteService {
        return retrofit.create(SatelliteService::class.java)
    }
}