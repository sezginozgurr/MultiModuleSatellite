package com.app.data.mapper

import com.app.data.model.SatelliteListResponse
import com.app.domain.model.SatelliteListUiModel
import javax.inject.Inject

class HomeMapper @Inject constructor() {
    fun toUi(list: List<SatelliteListResponse>) =
        list.map { dto ->
            SatelliteListUiModel(
                id = dto.id ?: -1,
                active = dto.active ?: false,
                name = dto.name.orEmpty()
            )
        }
}