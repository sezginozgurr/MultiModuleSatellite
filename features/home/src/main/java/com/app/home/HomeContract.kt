package com.app.home

internal object HomeContract {
    sealed interface UiEffect {
        data object NavigateDetail : UiEffect
    }
}