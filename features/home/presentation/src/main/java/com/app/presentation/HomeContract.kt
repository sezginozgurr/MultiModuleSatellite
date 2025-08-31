package com.app.presentation

internal object HomeContract {
    sealed interface UiEffect {
        data object NavigateDetail : UiEffect
    }
}