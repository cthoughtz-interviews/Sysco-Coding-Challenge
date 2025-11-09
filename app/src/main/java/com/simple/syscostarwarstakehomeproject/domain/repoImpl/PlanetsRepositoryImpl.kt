package com.simple.syscostarwarstakehomeproject.domain.repoImpl

import androidx.paging.PagingSource
import com.simple.syscostarwarstakehomeproject.data.paging.PlanetsPagingSource
import com.simple.syscostarwarstakehomeproject.data.repository.PlanetsRepository
import com.simple.syscostarwarstakehomeproject.domain.models.GetPlanetsResponse
import javax.inject.Inject

class PlanetsRepositoryImpl @Inject constructor(private val planetsPagingSource: PlanetsPagingSource) :
    PlanetsRepository {

    override fun getPlanetsPagingSource(): PagingSource<Int, GetPlanetsResponse.Planet> {
        return planetsPagingSource
    }
}