package com.app.domain.model

data class SatelliteDetailUiModel(
    val id: Int = -1,
    val costPerLaunch: Long = -1,
    val firstFlight: String = "",
    val height: Int? = -1,
    val mass: Int = -1,
)