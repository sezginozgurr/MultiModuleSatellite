package com.app.satellitecomposeproject

import androidx.lifecycle.ViewModel
import com.app.network.mvi.MVI
import com.app.satellitecomposeproject.MainContract.UiAction
import com.app.satellitecomposeproject.MainContract.UiState
import com.app.satellitecomposeproject.MainContract.UiEffect
import com.app.network.mvi.mvi
import javax.inject.Inject

class MainViewModel @Inject constructor() : ViewModel(),
    MVI<UiState, UiAction, UiEffect> by mvi(MainContract.UiState()) {
}