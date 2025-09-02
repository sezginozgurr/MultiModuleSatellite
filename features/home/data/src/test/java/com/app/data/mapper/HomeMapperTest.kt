package com.app.data.mapper

import com.app.data.model.SatelliteListResponse
import com.app.domain.model.SatelliteListUiModel
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class HomeMapperTest {

    private val mapper = HomeMapper()

    @Test
    fun `toUi maps list correctly when all fields are present`() {
        // Given
        val input = listOf(
            SatelliteListResponse(id = 1, active = true, name = "Alpha"),
            SatelliteListResponse(id = 2, active = false, name = "Beta")
        )

        // When
        val result = mapper.toUi(input)

        // Then
        val expected = listOf(
            SatelliteListUiModel(id = 1, active = true, name = "Alpha"),
            SatelliteListUiModel(id = 2, active = false, name = "Beta")
        )
        assertEquals(expected, result)
    }

    /**
     * isimlendirmeler given / when / then şeklinde de olabilir. Standartlar gözetilmelidir.
     * **/
    @Test
    fun given_null_fields_when_toUi_is_called_then_apply_defaults() {
        // Given
        val input = listOf(
            SatelliteListResponse(id = null, active = null, name = null)
        )

        // When
        val result = mapper.toUi(input)

        // Then
        val expected = listOf(
            SatelliteListUiModel(id = -1, active = false, name = "")
        )
        assertEquals(expected, result)
    }

    @Test
    fun `toUi returns empty list when input is empty`() {
        // Given
        val input = emptyList<SatelliteListResponse>()

        // When
        val result = mapper.toUi(input)

        // Then
        assertEquals(emptyList<SatelliteListUiModel>(), result)
    }
}


