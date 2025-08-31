package com.app.data.repository

import com.app.common.Resource
import com.app.common.map
import com.app.data.service.SatelliteService
import com.app.domain.model.SatelliteListUiModel
import com.app.domain.repository.SatelliteRepository
import com.app.network.safeApiCall
import javax.inject.Inject

internal class SatelliteRepositoryImpl @Inject constructor(
    private val api: SatelliteService,
) : SatelliteRepository {

    override suspend fun getSatelliteList(): Resource<List<SatelliteListUiModel>> {
        return safeApiCall { }.map { listOf() }
    }
}