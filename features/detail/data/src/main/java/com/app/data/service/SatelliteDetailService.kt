package com.app.data.service

import com.app.data.model.PositionsResponse
import com.app.data.model.SatelliteDetailResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface SatelliteDetailService {

    @GET("satellites/{id}")
    suspend fun getSatelliteDetail(
        @Path("id") id: Int
    ): SatelliteDetailResponse

    @GET("positions/{id}")
    suspend fun getPositions(@Path("id") id: Int): PositionsResponse
}