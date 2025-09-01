package com.app.data.service

import com.app.data.model.SatelliteDetailResponse
import retrofit2.http.GET

interface SatelliteDetailService {
    @GET("satellites")
    suspend fun getSatelliteDetail(): List<SatelliteDetailResponse>
}