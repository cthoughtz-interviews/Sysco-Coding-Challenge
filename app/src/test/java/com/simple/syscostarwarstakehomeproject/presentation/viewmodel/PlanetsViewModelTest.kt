package com.simple.syscostarwarstakehomeproject.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.cachedIn
import app.cash.turbine.test
import com.google.common.truth.ExpectFailure.assertThat
import com.google.common.truth.Truth.assertThat
import com.simple.syscostarwarstakehomeproject.data.paging.PlanetsPagingSource
import com.simple.syscostarwarstakehomeproject.data.remoteDataSource.PlanetsRemoteDataSource
import com.simple.syscostarwarstakehomeproject.domain.models.GetPlanetsResponse
import com.simple.syscostarwarstakehomeproject.domain.usecase.PlanetsUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions.*

@OptIn(ExperimentalCoroutinesApi::class)
class PlanetsViewModelTest {

    private lateinit var viewModel: PlanetsViewModel
    private val mockPlanetsUseCase: PlanetsUseCase = mockk()

    @Before
    fun setUp() {
        viewModel = PlanetsViewModel(mockPlanetsUseCase)
    }

    @Test
    fun `load should return page when api call succeeds`() = runTest {
        // Given
        val mockDataSource: PlanetsRemoteDataSource = mockk()
        val pagingSource = PlanetsPagingSource(mockDataSource)
        val expectedPlanets = listOf(
            GetPlanetsResponse.Planet("1", "25", "4888"),
            GetPlanetsResponse.Planet("2", "44", "98458")
        )
        val apiResponse = GetPlanetsResponse(
            count = 60,
            next = "page=2",
            previous = null,
            results = expectedPlanets
        )

        coEvery { mockDataSource.getPlanetsPage(1) } returns apiResponse

        // When
        val result = pagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 20,
                placeholdersEnabled = false
            )
        )

        // Then
        assertThat(result).isInstanceOf(PagingSource.LoadResult.Page::class.java)
        val pageResult = result as PagingSource.LoadResult.Page<Int, GetPlanetsResponse.Planet>
        assertThat(pageResult.data).isEqualTo(expectedPlanets)
        assertThat(pageResult.prevKey).isNull()
        assertThat(pageResult.nextKey).isEqualTo(2)
    }
}
