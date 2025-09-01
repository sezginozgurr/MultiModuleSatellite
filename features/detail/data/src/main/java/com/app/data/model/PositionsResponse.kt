package com.app.data.model

data class PositionsResponse(
    val list: List<SatellitePositions>
)

data class SatellitePositions(
    val id: String,
    val positions: List<PositionDto>
)

data class PositionDto(
    val posX: Double,
    val posY: Double
)
