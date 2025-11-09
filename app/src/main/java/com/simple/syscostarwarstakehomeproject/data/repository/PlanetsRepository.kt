package com.simple.syscostarwarstakehomeproject.data.repository

import androidx.paging.PagingSource
import com.simple.syscostarwarstakehomeproject.domain.models.GetPlanetsResponse

interface PlanetsRepository {
    fun getPlanetsPagingSource(): PagingSource<Int, GetPlanetsResponse.Planet>
}