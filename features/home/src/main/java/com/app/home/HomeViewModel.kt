package com.app.home

import androidx.lifecycle.ViewModel
import com.app.network.mvi.MVI
import com.app.network.mvi.mvi
import com.app.home.HomeContract.UiEffect
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(): ViewModel(),
    MVI<Unit, Unit, UiEffect> by mvi(Unit) {

}