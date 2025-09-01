package com.app.presentation

import com.app.domain.model.Positions
import com.app.domain.model.SatelliteDetailUiModel

object SatelliteDetailContract {
    data class DetailUiState(
        val isLoading: Boolean = false,
        val title: String = "",
        val currentPosition: Positions? = null,
        val detail: SatelliteDetailUiModel = SatelliteDetailUiModel(),
        val error: String? = null
    )

    sealed interface DetailUiAction {
        data object Load : DetailUiAction
        data object Retry : DetailUiAction
    }

    sealed interface DetailUiEffect {
        data class ShowToast(val message: String) : DetailUiEffect
    }
}