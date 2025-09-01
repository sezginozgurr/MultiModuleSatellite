package com.app.data.mapper

import com.app.data.model.PositionsResponse
import com.app.data.model.SatelliteDetailResponse
import com.app.domain.model.PositionUiModel
import com.app.domain.model.Positions
import com.app.domain.model.SatelliteDetailUiModel
import com.app.domain.model.SatellitePositionUiModel
import javax.inject.Inject

class SatelliteDetailMapper @Inject constructor() {
    fun mapToDetail(
        detail: SatelliteDetailResponse
    ): SatelliteDetailUiModel =
        SatelliteDetailUiModel(
            id = detail.id,
            costPerLaunch = detail.cost_per_launch,
            firstFlight = detail.first_flight,
            height = detail.height,
            mass = detail.mass
        )

    fun mapToPosition(response: PositionsResponse): SatellitePositionUiModel {
        return SatellitePositionUiModel(
            positionList = response.list.map { satellite ->
                PositionUiModel(
                    id = satellite.id,
                    positions = satellite.positions.map { pos ->
                        Positions(
                            posX = pos.posX,
                            posY = pos.posY
                        )
                    }
                )
            }
        )
    }

}