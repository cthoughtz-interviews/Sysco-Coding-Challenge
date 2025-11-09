package com.simple.syscostarwarstakehomeproject.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.simple.syscostarwarstakehomeproject.domain.usecase.PlanetsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class PlanetsViewModel @Inject constructor(val planetsUseCase: PlanetsUseCase) : ViewModel() {

    val planetsPagingFlow = Pager(
        config = PagingConfig(
            pageSize = 20,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { planetsUseCase.getPlanetsPagingSource() }
    ).flow.cachedIn(viewModelScope)
}