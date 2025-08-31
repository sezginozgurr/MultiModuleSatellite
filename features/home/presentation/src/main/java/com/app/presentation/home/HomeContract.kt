package com.app.presentation.home

internal object HomeContract {
    sealed interface UiEffect {
        data object NavigateDetail : UiEffect
    }
}