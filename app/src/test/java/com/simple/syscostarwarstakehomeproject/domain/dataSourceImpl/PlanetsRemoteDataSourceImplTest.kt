package com.simple.syscostarwarstakehomeproject.domain.dataSourceImpl

import com.google.common.truth.Truth.assertThat
import com.simple.syscostarwarstakehomeproject.data.network.ApiService
import com.simple.syscostarwarstakehomeproject.domain.models.GetPlanetsResponse
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows
import java.io.IOException
import kotlin.test.Test

@OptIn(ExperimentalCoroutinesApi::class)
class PlanetsRemoteDataSourceImplTest {

    private lateinit var dataSource: PlanetsRemoteDataSourceImpl
    private val mockApiService: ApiService = mockk()

    @Before
    fun setUp() {
        dataSource = PlanetsRemoteDataSourceImpl(mockApiService)
    }

    @Test
    fun `getPlanetsPage should return planets from api service`() = runTest {
        // Given
        val page = 1
        val expectedResponse = GetPlanetsResponse(
            count = 60,
            next = "next-page",
            previous = null,
            results = listOf(
                GetPlanetsResponse.Planet("1", "Earth", "Terrestrial"),
                GetPlanetsResponse.Planet("2", "Mars", "Terrestrial")
            )
        )

        coEvery { mockApiService.getPlanets(page) } returns expectedResponse

        // When
        val result = dataSource.getPlanetsPage(page)

        // Then
        assertThat(result).isEqualTo(expectedResponse)
        coVerify { mockApiService.getPlanets(page) }
    }

    @Test
    fun `getPlanetsPage should propagate api exceptions`() = runTest {
        // Given
        val page = 1
        val expectedException = IOException("Network error")

        coEvery { mockApiService.getPlanets(page) } throws expectedException

        // When & Then
        assertThrows<IOException> {
            dataSource.getPlanetsPage(page)
        }
        coVerify { mockApiService.getPlanets(page) }
    }
}