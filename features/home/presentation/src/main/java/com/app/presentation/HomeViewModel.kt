package com.app.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.common.fold
import com.app.domain.usecase.SatelliteListUseCase
import com.app.network.mvi.MVI
import com.app.network.mvi.mvi
import com.app.presentation.HomeContract.HomeUiAction
import com.app.presentation.HomeContract.HomeUiEffect
import com.app.presentation.HomeContract.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class HomeViewModel @Inject constructor(
    private val getSatelliteList: SatelliteListUseCase
) : ViewModel(),
    MVI<HomeUiState, HomeUiAction, HomeUiEffect> by mvi(HomeUiState(isLoading = true)) {

    init {
        onAction(HomeUiAction.Load)
    }

    override fun onAction(uiAction: HomeUiAction) {
        when (uiAction) {
            HomeUiAction.Load,
            HomeUiAction.Retry -> load()

            is HomeUiAction.QueryChanged -> {
                updateUiState { copy(query = uiAction.value) }
            }

            is HomeUiAction.ItemClicked -> {
                viewModelScope.launch {
                    emitUiEffect(HomeUiEffect.NavigateToDetails(uiAction.id))
                }
            }
        }
    }

    private fun load() = viewModelScope.launch {
        updateUiState { copy(isLoading = true, error = null) }

        getSatelliteList().fold(
            onSuccess = { list ->
                updateUiState {
                    copy(
                        isLoading = false,
                        satellites = list,
                        error = null
                    )
                }
            },
            onError = { ex ->
                val msg = ex.message.orEmpty().ifBlank { "Unknown error" }
                updateUiState { copy(isLoading = false, error = msg) }
                emitUiEffect(HomeUiEffect.ShowToast(msg))
            }
        )
    }
}
