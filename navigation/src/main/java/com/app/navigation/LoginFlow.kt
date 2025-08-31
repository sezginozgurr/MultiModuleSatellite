package com.app.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigation
import com.app.common.navigation.Screen
import com.app.splash.navigation.Splash
import com.app.splash.navigation.splashScreen
import kotlinx.serialization.Serializable

@Serializable
object LoginFlow : Screen

internal fun NavGraphBuilder.loginNavigation(navController: NavHostController) {
    navigation<LoginFlow>(Splash) {
        splashScreen(onNavigateHome = { })
    }
}