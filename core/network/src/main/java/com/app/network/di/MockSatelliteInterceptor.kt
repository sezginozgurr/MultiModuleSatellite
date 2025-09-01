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
import javax.inject.Singleton

@Singleton
class MockSatelliteInterceptor @Inject constructor(
    @param:ApplicationContext private val context: Context
) : Interceptor {

    private val listJson by lazy {
        context.resources.openRawResource(R.raw.satellite_list)
            .bufferedReader().use { it.readText() }
    }

    private val detailsArray by lazy {
        val txt = context.resources.openRawResource(R.raw.satellite_detail)
            .bufferedReader().use { it.readText() }
        org.json.JSONArray(txt)
    }

    private val positionsRoot by lazy {
        val txt = context.resources.openRawResource(R.raw.position)
            .bufferedReader().use { it.readText() }
        org.json.JSONObject(txt)
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val req = chain.request()
        if (req.method != "GET") return chain.proceed(req)

        val path = req.url.encodedPath

        return when {
            path.endsWith("/satellites") -> ok(req, listJson)

            path.matches(Regex(".*/satellites/\\d+$")) -> {
                val id = path.substringAfterLast("/").toInt()
                val obj = findDetailById(id)
                if (obj != null) ok(req, obj.toString()) else notFound(req, "Satellite $id not found")
            }

            path.matches(Regex(".*/positions/\\d+$")) -> {
                val id = path.substringAfterLast("/")
                val arr = findPositionsById(id)
                if (arr != null) {
                    val body = org.json.JSONObject().put("positions", arr).toString()
                    ok(req, body)
                } else {
                    notFound(req, "Positions for $id not found")
                }
            }

            else -> chain.proceed(req)
        }
    }

    private fun findDetailById(id: Int): org.json.JSONObject? {
        for (i in 0 until detailsArray.length()) {
            val obj = detailsArray.getJSONObject(i)
            if (obj.optInt("id") == id) return obj
        }
        return null
    }

    private fun findPositionsById(id: String): org.json.JSONArray? {
        val list = positionsRoot.getJSONArray("list")
        for (i in 0 until list.length()) {
            val obj = list.getJSONObject(i)
            if (obj.optString("id") == id) return obj.getJSONArray("positions")
        }
        return null
    }

    private fun ok(req: okhttp3.Request, body: String) =
        Response.Builder()
            .request(req)
            .protocol(Protocol.HTTP_1_1)
            .code(200)
            .message("OK")
            .body(body.toResponseBody("application/json".toMediaType()))
            .build()

    private fun notFound(req: okhttp3.Request, msg: String) =
        Response.Builder()
            .request(req)
            .protocol(Protocol.HTTP_1_1)
            .code(404)
            .message("Not Found")
            .body("""{"error":"$msg"}""".toResponseBody("application/json".toMediaType()))
            .build()
}