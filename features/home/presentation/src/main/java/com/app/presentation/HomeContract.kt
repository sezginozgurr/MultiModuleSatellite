package com.app.presentation

import com.app.domain.model.SatelliteListUiModel

internal object HomeContract {

    data class HomeUiState(
        val isLoading: Boolean = false,
        val query: String = "",
        val satellites: List<SatelliteListUiModel> = emptyList(),
        val error: String? = null
    )

    sealed interface HomeUiAction {
        data object Load : HomeUiAction
        data object Retry : HomeUiAction
        data class QueryChanged(val value: String) : HomeUiAction
        data class ItemClicked(val id: Int, val name: String) : HomeUiAction
    }

    sealed interface HomeUiEffect {
        data class NavigateToDetails(val id: Int, val name: String) : HomeUiEffect
        data class ShowToast(val message: String) : HomeUiEffect
    }
}