package com.simple.syscostarwarstakehomeproject.di

import com.simple.syscostarwarstakehomeproject.data.paging.PlanetsPagingSource
import com.simple.syscostarwarstakehomeproject.data.remoteDataSource.PlanetsRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PagingModule {

    @Provides
    @Singleton
    fun providePlanetsPagingSource(
        remoteDataSource: PlanetsRemoteDataSource
    ): PlanetsPagingSource {
        return PlanetsPagingSource(remoteDataSource)
    }
}