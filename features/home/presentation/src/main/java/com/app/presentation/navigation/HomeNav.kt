package com.app.presentation.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.app.common.navigation.Screen
import com.app.presentation.home.HomeScreen
import com.app.presentation.home.HomeViewModel
import kotlinx.serialization.Serializable

@Serializable
data object Home : Screen

fun NavGraphBuilder.homeScreen(
    onNavigateToDetail: (Int) -> Unit,
) {
    composable<Home> {
        val viewModel = hiltViewModel<HomeViewModel>()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        val uiEffect = viewModel.uiEffect
        HomeScreen(onNavigateToShipDetails = {
            onNavigateToDetail(it) //todo
        })
    }
}