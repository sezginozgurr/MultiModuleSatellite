package com.app.network.di

import android.content.Context
import com.app.resources.R
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import javax.inject.Inject

class MockSatelliteInterceptor @Inject constructor(
    @param:ApplicationContext private val context: Context
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val req = chain.request()
        val hit = req.method == "GET" && req.url.encodedPath.endsWith("/satellites")
        if (!hit) return chain.proceed(req)

        val json = context.resources
            .openRawResource(R.raw.satellite_list)
            .bufferedReader()
            .use { it.readText() }

        return Response.Builder()
            .request(req)
            .protocol(Protocol.HTTP_1_1)
            .code(200)
            .message("OK")
            .body(json.toResponseBody("application/json".toMediaType()))
            .build()
    }
}
