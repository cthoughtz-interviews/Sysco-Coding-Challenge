package com.simple.syscostarwarstakehomeproject.data.remoteDataSource

import com.simple.syscostarwarstakehomeproject.data.network.ApiService
import com.simple.syscostarwarstakehomeproject.domain.models.GetPlanetsResponse
import javax.inject.Inject

private const val TAG = "PlanetsRemoteDataSourceImpl"
class PlanetsRemoteDataSourceImpl @Inject constructor(val apiService: ApiService) : PlanetsRemoteDataSource{

    override suspend fun getPlanetsPage(page: Int): GetPlanetsResponse {
        return apiService.getPlanets(page)
    }
}