package com.simple.syscostarwarstakehomeproject.presentation.views.screens

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeGestures
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.simple.syscostarwarstakehomeproject.R
import com.simple.syscostarwarstakehomeproject.domain.models.GetPlanetsResponse
import com.simple.syscostarwarstakehomeproject.presentation.utils.CLIMATE_NAME
import com.simple.syscostarwarstakehomeproject.presentation.utils.DIAMETER
import com.simple.syscostarwarstakehomeproject.presentation.utils.GRAVITY
import com.simple.syscostarwarstakehomeproject.presentation.utils.IMAGE_NUMBER
import com.simple.syscostarwarstakehomeproject.presentation.utils.ORBITAL_PERIOD
import com.simple.syscostarwarstakehomeproject.presentation.utils.PLANET_NAME
import com.simple.syscostarwarstakehomeproject.presentation.utils.POPULATION
import com.simple.syscostarwarstakehomeproject.presentation.utils.ROTATIONAL_PERIOD
import com.simple.syscostarwarstakehomeproject.presentation.viewmodel.PlanetsViewModel
import com.simple.syscostarwarstakehomeproject.presentation.views.Components.CardItem
import com.simple.syscostarwarstakehomeproject.presentation.views.activities.DetailsActivity

private const val TAG = "PlanetScreen"

@Composable
fun PlanetScreen(viewModel: PlanetsViewModel = hiltViewModel()) {

    val lazyPlanetsItems = viewModel.planetsPagingFlow.collectAsLazyPagingItems()
    val context = LocalContext.current


    Column(
        Modifier
            .fillMaxWidth()
            .background(color = colorResource(R.color.dark_blue_background_color))
            .padding(top = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Star Wars Planet",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            color = colorResource(
                R.color.white
            ),
            modifier = Modifier.padding(bottom = 10.dp)
        )
        Text(
            "${lazyPlanetsItems.itemCount} planets discovered",
            fontSize = 15.sp,
            color = colorResource(R.color.secondary_text_label),
            modifier = Modifier.padding(bottom = 10.dp)
        )

        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth(),
            thickness = 2.dp,
            color = colorResource(R.color.card_stroke)
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorResource(R.color.background_color))
        ) {
            when (lazyPlanetsItems.loadState.refresh) {
                is LoadState.Loading -> {
                    val loading = lazyPlanetsItems.loadState.refresh as LoadState.Loading
                    Log.d(TAG, loading.toString())
                }

                is LoadState.Error -> {
                    val error = lazyPlanetsItems.loadState.refresh as LoadState.Error
                    Log.d(TAG, error.toString())
                    Toast.makeText(context, "$error", Toast.LENGTH_SHORT).show()

                }

                else -> {
                    Log.d(TAG, lazyPlanetsItems.itemSnapshotList.toString())
                    PlanetsList(lazyPlanetsItems = lazyPlanetsItems, context)
                }
            }
        }
    }
}

@Composable
fun PlanetsList(lazyPlanetsItems: LazyPagingItems<GetPlanetsResponse.Planet>, context: Context,) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = WindowInsets.safeGestures.asPaddingValues()
    ) {
        items(lazyPlanetsItems.itemCount){ index ->
            val planet = lazyPlanetsItems[index]
            CardItem(
                planetName = planet?.name ?: "N/A",
                planetClimate = planet?.climate ?: "N/A",
                planetTerrain = planet?.terrain ?: "N/A",
                planetImage = "https://picsum.photos/id/${index + 1}/300/300"
            ) {
                val intent = Intent(context, DetailsActivity::class.java)
                val bundle = Bundle().apply {
                    putString(IMAGE_NUMBER,(index+1).toString())
                    putString(PLANET_NAME, planet?.name ?: "NA")
                    putString(CLIMATE_NAME, planet?.climate ?: "NA")
                    putString(ORBITAL_PERIOD, planet?.orbitalPeriod)
                    putString(GRAVITY, planet?.gravity)
                    putString(POPULATION,planet?.population)
                    putString(DIAMETER, planet?.diameter)
                    putString(ROTATIONAL_PERIOD, planet?.rotationPeriod)
                }
                intent.putExtras(bundle)
                context.startActivity(intent)
            }
        }
    }
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun MainScreenPreview() {
    val viewModel: PlanetsViewModel = hiltViewModel()
    PlanetScreen(viewModel)
}