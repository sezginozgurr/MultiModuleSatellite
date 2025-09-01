package com.app.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.app.common.fold
import com.app.common.manager.DataStoreManager
import com.app.domain.model.Positions
import com.app.domain.usecase.GetSatelliteDetailAndPositionsUseCase
import com.app.network.mvi.MVI
import com.app.network.mvi.mvi
import com.app.presentation.SatelliteDetailContract.DetailUiAction
import com.app.presentation.SatelliteDetailContract.DetailUiEffect
import com.app.presentation.SatelliteDetailContract.DetailUiState
import com.app.presentation.navigation.Detail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SatelliteDetailViewModel @Inject constructor(
    val getDetail: GetSatelliteDetailAndPositionsUseCase,
    private val dataStore: DataStoreManager,
    savedStateHandle: SavedStateHandle
) : ViewModel(),
    MVI<DetailUiState, DetailUiAction, DetailUiEffect> by mvi(DetailUiState(isLoading = true)) {

    private val args: Detail = savedStateHandle.toRoute()
    private var job: Job? = null

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
            onSuccess = { (detail, _) ->
                updateUiState {
                    copy(
                        isLoading = false,
                        detail = detail,
                        title = args.name,
                        error = null
                    )
                }
                startPositionTicker()
            },
            onError = { ex ->
                val msg = ex.message.orEmpty().ifBlank { "Unknown error" }
                updateUiState { copy(isLoading = false, error = msg) }
                emitUiEffect(DetailUiEffect.ShowToast(msg))
            }
        )
    }

    private fun startPositionTicker() {
        job?.cancel()
        job = viewModelScope.launch {
            dataStore.observePositions(args.id).collectLatest { list ->
                if (list.isEmpty()) {
                    updateUiState { copy(currentPosition = null) }
                    return@collectLatest
                }
                var index = 0
                while (isActive) {
                    val p = list[index % list.size]
                    updateUiState { copy(currentPosition = Positions(p.posX, p.posY)) }
                    index++
                    delay(3_000)
                }
            }
        }
    }

    override fun onCleared() {
        job?.cancel()
        super.onCleared()
    }
}