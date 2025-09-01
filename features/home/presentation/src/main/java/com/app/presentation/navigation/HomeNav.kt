package com.app.presentation.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.app.common.navigation.Screen
import com.app.presentation.HomeScreenRoute
import com.app.presentation.HomeViewModel
import kotlinx.serialization.Serializable

@Serializable
data object Home : Screen

fun NavGraphBuilder.homeScreen(
    onNavigateToDetail: (Int, String) -> Unit,
) {
    composable<Home> {
        val viewModel = hiltViewModel<HomeViewModel>()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        val uiEffect = viewModel.uiEffect
        HomeScreenRoute(
            uiState = uiState,
            uiEffect = uiEffect,
            onAction = viewModel::onAction,
            onShipClick = onNavigateToDetail
        )
    }
}