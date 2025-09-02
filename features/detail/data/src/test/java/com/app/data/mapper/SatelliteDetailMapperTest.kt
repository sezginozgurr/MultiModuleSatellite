import com.app.data.mapper.SatelliteDetailMapper

import com.app.data.model.PositionDto
import com.app.data.model.PositionsResponse
import com.app.data.model.SatelliteDetailResponse
import com.app.data.model.SatellitePositions
import com.app.domain.model.PositionUiModel
import com.app.domain.model.Positions
import com.app.domain.model.SatelliteDetailUiModel
import com.app.domain.model.SatellitePositionUiModel
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class SatelliteDetailMapperTest {

    private val mapper = SatelliteDetailMapper()

    @Test
    fun `mapToDetail maps fields correctly`() {
        // Given
        val response = SatelliteDetailResponse(
            id = 42,
            cost_per_launch = 1000000L,
            first_flight = "2024-01-01",
            height = 70,
            mass = 500
        )

        // When
        val result = mapper.mapToDetail(response)

        // Then
        val expected = SatelliteDetailUiModel(
            id = 42,
            costPerLaunch = 1000000L,
            firstFlight = "2024-01-01",
            height = 70,
            mass = 500
        )
        assertEquals(expected, result)
    }

    /**
     * isimlendirmeler given / when / then şeklinde de olabilir. Standartlar gözetilmelidir.
     * **/
    @Test
    fun given_nullable_height_when_mapToDetail_then_preserves_nullable_height_and_defaults() {
        // Given
        val response = SatelliteDetailResponse(
            id = -1,
            cost_per_launch = -1,
            first_flight = "",
            height = null,
            mass = -1
        )

        // When
        val result = mapper.mapToDetail(response)

        // Then
        val expected = SatelliteDetailUiModel(
            id = -1,
            costPerLaunch = -1,
            firstFlight = "",
            height = null,
            mass = -1
        )
        assertEquals(expected, result)
    }

    @Test
    fun `mapToPosition maps nested lists correctly`() {
        // Given
        val response = PositionsResponse(
            list = listOf(
                SatellitePositions(
                    id = "sat-1",
                    positions = listOf(
                        PositionDto(posX = 1.0, posY = 2.0),
                        PositionDto(posX = 3.0, posY = 4.0)
                    )
                ),
                SatellitePositions(
                    id = "sat-2",
                    positions = listOf(
                        PositionDto(posX = -1.5, posY = 0.0)
                    )
                )
            )
        )

        // When
        val result = mapper.mapToPosition(response)

        // Then
        val expected = SatellitePositionUiModel(
            positionList = listOf(
                PositionUiModel(
                    id = "sat-1",
                    positions = listOf(
                        Positions(posX = 1.0, posY = 2.0),
                        Positions(posX = 3.0, posY = 4.0)
                    )
                ),
                PositionUiModel(
                    id = "sat-2",
                    positions = listOf(
                        Positions(posX = -1.5, posY = 0.0)
                    )
                )
            )
        )
        assertEquals(expected, result)
    }

    @Test
    fun `mapToPosition handles empty lists`() {
        // Given
        val response = PositionsResponse(list = emptyList())

        // When
        val result = mapper.mapToPosition(response)

        // Then
        val expected = SatellitePositionUiModel(positionList = emptyList())
        assertEquals(expected, result)
    }
}


