package com.simple.syscostarwarstakehomeproject.domain.usecase

import androidx.paging.PagingSource
import com.simple.syscostarwarstakehomeproject.data.repository.PlanetsRepository
import com.simple.syscostarwarstakehomeproject.domain.models.GetPlanetsResponse
import javax.inject.Inject

class PlanetsUseCase @Inject constructor(val planetRepo: PlanetsRepository) {

    fun getPlanetsPagingSource(): PagingSource<Int, GetPlanetsResponse.Planet> {
        return planetRepo.getPlanetsPagingSource()
    }
}