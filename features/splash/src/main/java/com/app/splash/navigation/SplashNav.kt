package com.app.splash.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.app.common.navigation.Screen
import com.app.splash.SplashScreen
import com.app.splash.SplashViewModel
import kotlinx.serialization.Serializable

@Serializable
data object Splash : Screen

fun NavGraphBuilder.splashScreen(
    onNavigateHome: () -> Unit,
) {
    composable<Splash> {
        val viewModel = hiltViewModel<SplashViewModel>()
        val uiEffect = viewModel.uiEffect
        SplashScreen(
            uiEffect = uiEffect,
            onNavigateToHome = onNavigateHome
        )
    }
}