package com.simple.syscostarwarstakehomeproject.data.remoteDataSource

import com.simple.syscostarwarstakehomeproject.domain.models.GetPlanetsResponse

interface PlanetsRemoteDataSource {

    suspend fun getPlanetsPage(page: Int): GetPlanetsResponse
}