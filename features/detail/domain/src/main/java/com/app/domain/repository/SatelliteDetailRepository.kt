package com.app.domain.repository

import com.app.common.Resource
import com.app.domain.model.SatelliteDetailUiModel
import com.app.domain.model.SatellitePositionUiModel

interface SatelliteDetailRepository {
    suspend fun getSatelliteDetail(id: Int): Resource<SatelliteDetailUiModel>

    suspend fun getPositions(id: Int): Resource<SatellitePositionUiModel>
}