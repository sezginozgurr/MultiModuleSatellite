package com.app.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.network.mvi.MVI
import com.app.network.mvi.mvi
import com.app.splash.SplashContract.UiEffect
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor() : ViewModel(),
    MVI<Unit, Unit, UiEffect> by mvi(Unit) {

    init {
        viewModelScope.launch {
            delay(3000)
            emitUiEffect(SplashContract.UiEffect.NavigateHome)
        }
    }

}