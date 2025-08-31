package com.app.domain.repository

import com.app.common.Resource
import com.app.domain.model.SatelliteListUiModel

interface SatelliteRepository {

    suspend fun getSatelliteList(): Resource<List<SatelliteListUiModel>>
}