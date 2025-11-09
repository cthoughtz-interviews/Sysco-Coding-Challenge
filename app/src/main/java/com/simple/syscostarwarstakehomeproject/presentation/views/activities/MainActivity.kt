package com.simple.syscostarwarstakehomeproject.presentation.views.activities

import android.R.attr.fragment
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.simple.syscostarwarstakehomeproject.presentation.views.screens.PlanetScreen
import androidx.navigation.fragment.fragment
import dagger.hilt.android.AndroidEntryPoint
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Surface {
                PlanetScreen()
            }
        }
    }
}
