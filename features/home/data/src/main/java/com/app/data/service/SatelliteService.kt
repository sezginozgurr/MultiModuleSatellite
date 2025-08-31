package com.app.data.service

import com.app.data.model.SatelliteListResponse
import retrofit2.http.GET

interface SatelliteService {

    @GET("satellites")
    suspend fun getSatelliteList(): List<SatelliteListResponse>

}