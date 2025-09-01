package com.app.domain.repository

import com.app.common.Resource
import com.app.domain.model.SatelliteDetailUiModel

interface SatelliteDetailRepository {
    suspend fun getSatelliteDetail(id: Int): Resource<SatelliteDetailUiModel>
}