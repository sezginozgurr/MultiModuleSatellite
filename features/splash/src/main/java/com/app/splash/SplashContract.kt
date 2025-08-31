package com.app.splash

internal object SplashContract {
    sealed interface UiEffect {
        data object NavigateHome : UiEffect
    }
}