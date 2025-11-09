package com.simple.syscostarwarstakehomeproject.domain.usecase

import androidx.paging.PagingSource
import com.google.common.truth.Truth.assertThat
import com.simple.syscostarwarstakehomeproject.data.repository.PlanetsRepository
import com.simple.syscostarwarstakehomeproject.domain.models.GetPlanetsResponse
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import kotlin.test.Test

class PlanetsUseCaseTest {

    private lateinit var planetsUseCase: PlanetsUseCase
    private val mockPlanetRepo: PlanetsRepository = mockk()

    @Before
    fun setUp() {
        planetsUseCase = PlanetsUseCase(mockPlanetRepo)
    }

    @Test
    fun `getPlanetsPagingSource should return paging source from repository`() {
        // Given
        val expectedPagingSource: PagingSource<Int, GetPlanetsResponse.Planet> = mockk()
        every { mockPlanetRepo.getPlanetsPagingSource() } returns expectedPagingSource

        // When
        val result = planetsUseCase.getPlanetsPagingSource()

        // Then
        assertThat(result).isEqualTo(expectedPagingSource)
        verify { mockPlanetRepo.getPlanetsPagingSource() }
    }
}