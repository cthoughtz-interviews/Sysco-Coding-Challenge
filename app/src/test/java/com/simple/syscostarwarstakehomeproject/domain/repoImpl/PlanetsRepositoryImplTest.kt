package com.simple.syscostarwarstakehomeproject.domain.repoImpl

import com.google.common.truth.Truth.assertThat
import com.simple.syscostarwarstakehomeproject.data.paging.PlanetsPagingSource
import io.mockk.mockk
import org.junit.Before
import kotlin.test.Test

class PlanetsRepositoryImplTest {

    private lateinit var repository: PlanetsRepositoryImpl
    private val mockPlanetsPagingSource: PlanetsPagingSource = mockk()

    @Before
    fun setUp() {
        repository = PlanetsRepositoryImpl(mockPlanetsPagingSource)
    }

    @Test
    fun `getPlanetsPagingSource should return the provided paging source`() {
        // When
        val result = repository.getPlanetsPagingSource()

        // Then
        assertThat(result).isEqualTo(mockPlanetsPagingSource)
    }
}