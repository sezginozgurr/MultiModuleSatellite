package com.app.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.app.common.fold
import com.app.domain.usecase.GetSatelliteDetailAndPositionsUseCase
import com.app.domain.usecase.PositionUseCase
import com.app.network.mvi.MVI
import com.app.network.mvi.mvi
import com.app.presentation.SatelliteDetailContract.DetailUiAction
import com.app.presentation.SatelliteDetailContract.DetailUiEffect
import com.app.presentation.SatelliteDetailContract.DetailUiState
import com.app.presentation.navigation.Detail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SatelliteDetailViewModel @Inject constructor(
    val getDetail: GetSatelliteDetailAndPositionsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel(),
    MVI<DetailUiState, DetailUiAction, DetailUiEffect> by mvi(DetailUiState(isLoading = true)) {

    private val args: Detail = savedStateHandle.toRoute()

    init {
        onAction(DetailUiAction.Load)
    }

    override fun onAction(uiAction: DetailUiAction) {
        when (uiAction) {
            DetailUiAction.Load,
            DetailUiAction.Retry -> loadDetail()
        }
    }

    private fun loadDetail() = viewModelScope.launch {
        updateUiState { copy(isLoading = true, error = null) }

        getDetail(args.id).fold(
            onSuccess = { (detail, positions) ->
                updateUiState {
                    copy(
                        isLoading = false,
                        detail = detail,
                        positions = positions.positionList,
                        error = null
                    )
                }
            },
            onError = { ex ->
                val msg = ex.message.orEmpty().ifBlank { "Unknown error" }
                updateUiState { copy(isLoading = false, error = msg) }
                emitUiEffect(DetailUiEffect.ShowToast(msg))
            }
        )
    }
}