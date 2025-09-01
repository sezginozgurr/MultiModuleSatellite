package com.app.domain.usecase

import com.app.common.Resource
import com.app.common.extension.combineResult
import com.app.domain.model.SatelliteDetailUiModel
import com.app.domain.model.SatellitePositionUiModel
import com.app.domain.repository.SatelliteDetailRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.supervisorScope
import javax.inject.Inject

class GetSatelliteDetailAndPositionsUseCase @Inject constructor(
    private val repository: SatelliteDetailRepository
) {
    suspend operator fun invoke(id: Int): Resource<Pair<SatelliteDetailUiModel, SatellitePositionUiModel>> =
        supervisorScope {
            val detailDeferred = async { repository.getSatelliteDetail(id) }
            val positionsDeferred = async { repository.getPositions(id) }

            combineResult(
                restResult1 = detailDeferred.await(),
                restResult2 = positionsDeferred.await()
            ) { detail, positions ->
                Pair(detail, positions)
            }
        }
}