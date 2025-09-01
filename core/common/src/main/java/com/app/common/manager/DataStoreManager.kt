package com.app.common.manager

import kotlinx.coroutines.flow.first
import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

private const val DATASTORE_NAME = "satellite_positions_datastore"
private val Context.dataStore by preferencesDataStore(DATASTORE_NAME)

data class StoredPosition(
    val posX: Double = 0.0,
    val posY: Double = 0.0
)

class DataStoreManager@Inject constructor(
    @param:ApplicationContext private val context: Context,
    private val gson: Gson
) {
    private fun positionsKey(id: Int): Preferences.Key<String> =
        stringPreferencesKey("positions_$id")

    suspend fun savePositions(id: Int, positions: List<StoredPosition>) {
        val json = gson.toJson(positions)
        context.dataStore.edit { prefs -> prefs[positionsKey(id)] = json }
    }

    fun observePositions(id: Int): Flow<List<StoredPosition>> {
        val type = object : TypeToken<List<StoredPosition>>() {}.type
        return context.dataStore.data.map { prefs ->
            val json = prefs[positionsKey(id)]
            if (json.isNullOrBlank()) emptyList()
            else runCatching { gson.fromJson<List<StoredPosition>>(json, type) }.getOrElse { emptyList() }
        }
    }

    suspend fun getPositionsOnce(id: Int): List<StoredPosition> =
        runCatching { observePositions(id).first() }.getOrElse { emptyList() }

    suspend fun clearPositions(id: Int) {
        context.dataStore.edit { prefs -> prefs.remove(positionsKey(id)) }
    }
}
