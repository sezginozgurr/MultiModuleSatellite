package com.app.data.repository

import com.app.common.Resource
import com.app.common.map
import com.app.data.mapper.SatelliteDetailMapper
import com.app.data.service.SatelliteDetailService
import com.app.domain.model.SatelliteDetailUiModel
import com.app.domain.repository.SatelliteDetailRepository
import com.app.network.safeApiCall
import javax.inject.Inject

class SatelliteDetailRepositoryImpl @Inject constructor(
    private val api: SatelliteDetailService,
    private val mapper: SatelliteDetailMapper
) : SatelliteDetailRepository {

    override suspend fun getSatelliteDetail(): Resource<List<SatelliteDetailUiModel>> {
        return safeApiCall { api.getSatelliteDetail() }
            .map { mapper.mapToDetail(it) }
    }
}