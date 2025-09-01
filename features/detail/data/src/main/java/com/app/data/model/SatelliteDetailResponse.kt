package com.app.data.model

data class SatelliteDetailResponse(
    val id: Int = -1,
    val cost_per_launch: Long = -1,
    val first_flight: String = "",
    val height: Int? = -1,
    val mass: Int = -1,
)
