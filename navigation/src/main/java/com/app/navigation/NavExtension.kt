package com.app.navigation

import androidx.navigation.NavHostController
import com.app.common.navigation.Screen

fun NavHostController.navigateWithPopUpTo(screen: Any, popUp: Any) {
    navigate(screen) {
        popUpTo(popUp) { inclusive = true }
    }
}

fun Screen.getRoute(): String {
    return this::class.java.canonicalName.orEmpty()
}