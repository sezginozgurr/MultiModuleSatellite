package com.app.domain.usecase

import com.app.common.Resource
import com.app.domain.model.SatellitePositionUiModel
import com.app.domain.repository.SatelliteDetailRepository
import javax.inject.Inject

class PositionUseCase @Inject constructor(
    private val repository: SatelliteDetailRepository,
) {
    suspend operator fun invoke(id: Int): Resource<SatellitePositionUiModel> =
        repository.getPositions(id)
}