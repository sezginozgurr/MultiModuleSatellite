package com.app.data.mapper

import com.app.data.model.SatelliteDetailResponse
import com.app.domain.model.SatelliteDetailUiModel
import javax.inject.Inject

class SatelliteDetailMapper @Inject constructor() {
    fun mapToDetail(
        response: List<SatelliteDetailResponse>
    ): List<SatelliteDetailUiModel> = response.map { detail ->
        SatelliteDetailUiModel(
            id = detail.id,
            costPerLaunch = detail.cost_per_launch,
            firstFlight = detail.first_flight,
            height = detail.height,
            mass = detail.mass
        )
    }
}