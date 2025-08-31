package com.app.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigation
import com.app.common.navigation.Screen
import kotlinx.serialization.Serializable

@Serializable
object HomeFlow : Screen

internal fun NavGraphBuilder.homeNavigation(navController: NavHostController) {
    navigation<HomeFlow>(Home) {
        homeScreen(onNavigateToDetail = { navController.popBackStack() })
    }
}