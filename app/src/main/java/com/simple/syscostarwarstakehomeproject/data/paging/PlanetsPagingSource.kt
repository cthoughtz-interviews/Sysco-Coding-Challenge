package com.simple.syscostarwarstakehomeproject.data.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.simple.syscostarwarstakehomeproject.data.remoteDataSource.PlanetsRemoteDataSource
import com.simple.syscostarwarstakehomeproject.domain.models.GetPlanetsResponse.Planet
import javax.inject.Inject

private const val TAG = "PlanetsPagingSource"
class PlanetsPagingSource @Inject constructor(
    private val remoteDataSource: PlanetsRemoteDataSource
) : PagingSource<Int, Planet>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Planet> {
        return try {
            val page = params.key ?: 1
            Log.d(TAG,"Loading page: $page")

            val response = remoteDataSource.getPlanetsPage(page)
            Log.d(TAG,"Successfully loaded ${response.results?.size} planets")
            LoadResult.Page(
                data = response.results?.filterNotNull() ?: emptyList(),
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.next == null) null else page + 1
            )
        } catch (e: Exception) {
            Log.d(TAG,"Error loading page: ${e.message}")
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Planet>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}