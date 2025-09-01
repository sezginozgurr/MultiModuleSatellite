package com.app.common.manager.di

import android.content.Context
import com.app.common.manager.DataStoreManager
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @Singleton
    fun provideGson(): Gson = Gson()

    @Provides @Singleton
    fun provideDataStoreManager(@ApplicationContext context: Context): DataStoreManager =
        DataStoreManager(context)
}