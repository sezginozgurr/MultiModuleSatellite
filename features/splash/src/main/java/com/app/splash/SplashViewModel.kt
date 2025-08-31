package com.app.splash

import androidx.lifecycle.ViewModel
import com.app.network.mvi.MVI
import com.app.network.mvi.mvi
import com.app.splash.SplashContract.UiEffect
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor() : ViewModel(),
    MVI<Unit, Unit, UiEffect> by mvi(Unit) {

}