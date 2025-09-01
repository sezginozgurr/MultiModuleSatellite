package com.app.domain.model

data class SatellitePositionUiModel(
    val positionList: List<PositionUiModel> = emptyList()
)

data class PositionUiModel(
    val id: String = "",
    val positions: List<Positions> = emptyList()
)

data class Positions(
    val posX: Double = 0.0,
    val posY: Double = 0.0
)

