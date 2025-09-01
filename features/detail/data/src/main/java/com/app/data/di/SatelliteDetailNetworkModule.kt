package com.app.data.di

import com.app.data.service.SatelliteDetailService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object SatelliteDetailNetworkModule {

    @Provides
    @Singleton
    fun provideCategoryApi(retrofit: Retrofit): SatelliteDetailService {
        return retrofit.create(SatelliteDetailService::class.java)
    }
}