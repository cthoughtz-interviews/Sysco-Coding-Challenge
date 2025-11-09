package com.simple.syscostarwarstakehomeproject.data.network

import com.simple.syscostarwarstakehomeproject.domain.models.GetPlanetsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("planets")
    suspend fun getPlanets(@Query("page") page: Int ) : GetPlanetsResponse
}