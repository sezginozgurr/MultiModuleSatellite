package com.app.satellitecomposeproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.app.common.extension.collectWithLifecycle
import com.app.navigation.HomeFlow
import com.app.navigation.LoginFlow
import com.app.navigation.SatelliteAppNavGraph
import com.app.navigation.navigateWithPopUpTo
import com.app.uikit.theme.SatelliteComposeProjectTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            viewModel.uiEffect.collectWithLifecycle {
                when (it) {
                    is MainContract.UiEffect.NavigateLogin -> {
                        navController.navigateWithPopUpTo(LoginFlow, HomeFlow)
                    }
                }
            }
            SatelliteComposeProjectTheme {
                Box {
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        containerColor = MaterialTheme.colorScheme.primary,
                        content = { innerPadding ->
                            SatelliteAppNavGraph(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(innerPadding),
                                navController = navController,
                            )
                        },
                    )
                }
            }
        }
    }
}