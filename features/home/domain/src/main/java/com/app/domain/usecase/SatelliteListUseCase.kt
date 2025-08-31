package com.app.domain.usecase

import com.app.common.Resource
import com.app.domain.model.SatelliteListUiModel
import com.app.domain.repository.SatelliteRepository
import javax.inject.Inject

class SatelliteListUseCase @Inject constructor(
    private val repository: SatelliteRepository,
) {
    suspend operator fun invoke(): Resource<List<SatelliteListUiModel>> =
        repository.getSatelliteList()
}