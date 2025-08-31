package com.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.app.splash.navigation.Splash
import com.app.splash.navigation.splashScreen

@Composable
fun SatelliteAppNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = Splash,
        modifier = modifier,
    ) {
        splashScreen(
            onNavigateHome = { navController.navigateWithPopUpTo(HomeFlow, Splash) }
        )
        homeNavigation(navController)
    }
}