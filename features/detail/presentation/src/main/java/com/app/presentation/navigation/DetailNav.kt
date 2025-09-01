package com.app.presentation.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.app.common.navigation.Screen
import com.app.presentation.SatelliteDetailScreen
import com.app.presentation.SatelliteDetailViewModel
import kotlinx.serialization.Serializable

@Serializable
data class Detail(val id: Int, val name: String) : Screen

fun NavGraphBuilder.detailScreen(
    onNavigateBack: () -> Unit,
) {
    composable<Detail> {
        val viewModel = hiltViewModel<SatelliteDetailViewModel>()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        SatelliteDetailScreen(
            uiState = uiState,
            onNavigateBack = { onNavigateBack() }
        )
    }
}