package com.simple.syscostarwarstakehomeproject.di

import com.simple.syscostarwarstakehomeproject.data.remoteDataSource.PlanetsRemoteDataSource
import com.simple.syscostarwarstakehomeproject.data.remoteDataSource.PlanetsRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    @Singleton
    abstract fun bindPlanetsRemoteDataSource(
        planetsRemoteDataSourceImpl: PlanetsRemoteDataSourceImpl
    ) : PlanetsRemoteDataSource
}