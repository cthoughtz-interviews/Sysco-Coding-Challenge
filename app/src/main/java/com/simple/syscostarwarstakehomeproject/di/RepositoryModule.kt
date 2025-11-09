package com.simple.syscostarwarstakehomeproject.di

import com.simple.syscostarwarstakehomeproject.data.remoteDataSource.PlanetsRemoteDataSource
import com.simple.syscostarwarstakehomeproject.data.remoteDataSource.PlanetsRemoteDataSourceImpl
import com.simple.syscostarwarstakehomeproject.data.repository.PlanetsRepository
import com.simple.syscostarwarstakehomeproject.data.repository.PlanetsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindPlanetsRepository(
        planetsRepositoryImpl: PlanetsRepositoryImpl
    ) : PlanetsRepository
}