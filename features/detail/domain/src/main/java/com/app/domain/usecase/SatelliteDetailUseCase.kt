package com.app.domain.usecase

import com.app.common.Resource
import com.app.domain.model.SatelliteDetailUiModel
import com.app.domain.repository.SatelliteDetailRepository
import javax.inject.Inject

class SatelliteDetailUseCase @Inject constructor(
    private val repository: SatelliteDetailRepository,
) {
    suspend operator fun invoke(): Resource<List<SatelliteDetailUiModel>> =
        repository.getSatelliteDetail()
}