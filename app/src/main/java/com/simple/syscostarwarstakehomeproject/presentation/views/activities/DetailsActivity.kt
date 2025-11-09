package com.simple.syscostarwarstakehomeproject.presentation.views.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.isDigitsOnly
import coil.load
import com.simple.syscostarwarstakehomeproject.R
import com.simple.syscostarwarstakehomeproject.databinding.ActivityDetailBinding
import com.simple.syscostarwarstakehomeproject.presentation.utils.CLIMATE_NAME
import com.simple.syscostarwarstakehomeproject.presentation.utils.DIAMETER
import com.simple.syscostarwarstakehomeproject.presentation.utils.GRAVITY
import com.simple.syscostarwarstakehomeproject.presentation.utils.IMAGE_NUMBER
import com.simple.syscostarwarstakehomeproject.presentation.utils.ORBITAL_PERIOD
import com.simple.syscostarwarstakehomeproject.presentation.utils.PLANET_NAME
import com.simple.syscostarwarstakehomeproject.presentation.utils.POPULATION
import com.simple.syscostarwarstakehomeproject.presentation.utils.ROTATIONAL_PERIOD

class DetailsActivity : AppCompatActivity() {

    var binding: ActivityDetailBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val imageNumberArgs = intent.getStringExtra(IMAGE_NUMBER)
        val planetNameArgs = intent.getStringExtra(PLANET_NAME)
        val climateNameArgs = intent.getStringExtra(CLIMATE_NAME)
        val orbitalPeriodArgs = intent.getStringExtra(ORBITAL_PERIOD)
        val gravityArgs = intent.getStringExtra(GRAVITY)
        val populationArgs = intent.getStringExtra(POPULATION)
        val diameterArgs = intent.getStringExtra(DIAMETER)
        val rotationalPeriod = intent.getStringExtra(ROTATIONAL_PERIOD)

        Log.d("CheckImage", "$imageNumberArgs")

        uiSetup(
            imageNumberArgs,
            planetNameArgs,
            climateNameArgs,
            orbitalPeriodArgs,
            gravityArgs,
            populationArgs,
            diameterArgs,
            rotationalPeriod
        )
    }

    private fun uiSetup(
        imageNumberArgs: String?,
        planetNameArgs: String?,
        climateNameArgs: String?,
        orbitalPeriodArgs: String?,
        gravityArgs: String?,
        populationArgs: String?,
        diameterArgs: String?,
        rotationalArgs: String?
    ) {
        binding?.apply {
            backArrow.setOnClickListener {
                val intent = Intent(this@DetailsActivity, MainActivity::class.java)
                startActivity(intent)
            }

            if (imageNumberArgs != null) {
                planetDetailsBackgroundImage.load("https://picsum.photos/id/$imageNumberArgs/700/500") {
                    placeholder(R.drawable.loading_image)
                }
            } else {
                planetDetailsBackgroundImage.load("https://picsum.photos/id/827/700/500") {
                    placeholder(R.drawable.loading_image)
                }
            }

            planetName.text = planetNameArgs

            planetClimate.text = climateNameArgs?.split(",")?.get(0)

            orbitalPeriod.text = orbitalPeriodArgs

            gravity.text = gravityArgs

            populationAmount.text = if (populationArgs?.isDigitsOnly() == true) {
                populationArgs.toLong().withCommas()
            } else {
                populationArgs
            }

            diameterAmount.text = if (diameterArgs?.isDigitsOnly() == true) {
                " ${diameterArgs.toLong().withCommas()} km"
            } else {
                diameterArgs
            }

            rotationalPeriod.text = "$rotationalArgs Hr"
        }
    }

    private fun Long.withCommas(): String = java.text.DecimalFormat("#,###").format(this)

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}