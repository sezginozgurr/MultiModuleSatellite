package com.app.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.uikit.components.AppSearchBar
import com.app.uikit.theme.divider
import com.app.uikit.theme.onSurface
import com.app.uikit.theme.onSurfaceVariant
import com.app.uikit.theme.screenBg
import com.app.uikit.theme.statusGreen
import com.app.uikit.theme.statusRed

enum class ShipStatus { Active, Passive }
data class Ship(val name: String, val status: ShipStatus, val id: Int)

@Composable
fun HomeScreenRoute(
    onShipClick: (Int) -> Unit = {},
    onSearchSubmit: (String) -> Unit = {}
) {
    val ships = remember {
        listOf(
            Ship("Starship-1", ShipStatus.Passive, 1),
            Ship("Dragon-1", ShipStatus.Active, 2),
            Ship("Starship-3", ShipStatus.Active, 3)
        )
    }

    HomeScreen(
        allShips = ships,
        onSearchSubmit = onSearchSubmit,
        onNavigateToShipDetails = onShipClick
    )
}

@Composable
fun HomeScreen(
    allShips: List<Ship> = emptyList(),
    onSearchSubmit: (String) -> Unit = {},
    onNavigateToShipDetails: (Int) -> Unit = {}
) {
    var query by remember { mutableStateOf("") }

    val filtered = remember(query, allShips) {
        if (query.isBlank()) allShips
        else allShips.filter { it.name.contains(query, ignoreCase = true) }
    }

    Surface(color = screenBg, modifier = Modifier.fillMaxSize()) {
        Column(Modifier.fillMaxSize()) {

            Spacer(Modifier.height(16.dp))

            AppSearchBar(
                query = query,
                onQueryChange = { query = it },
                onSearch = onSearchSubmit,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(Modifier.height(8.dp))

            if (filtered.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 32.dp),
                    contentAlignment = Alignment.TopCenter
                ) {
                    Text(
                        "No results",
                        color = onSurfaceVariant,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(top = 12.dp, bottom = 24.dp)
                ) {
                    itemsIndexed(
                        items = filtered,
                        key = { _, item -> item.name }
                    ) { index, item ->
                        ShipRow(
                            ship = item,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { onNavigateToShipDetails(item.id) }
                        )

                        if (index < filtered.lastIndex) {
                            HorizontalDivider(
                                modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                                thickness = 0.8.dp,
                                color = divider.copy(alpha = 0.9f)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ShipRow(
    ship: Ship,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        val dotColor = when (ship.status) {
            ShipStatus.Active -> statusGreen
            ShipStatus.Passive -> statusRed
        }
        Box(
            modifier = Modifier
                .size(16.dp)
                .clip(CircleShape)
                .background(dotColor)
                .semantics(mergeDescendants = true) {
                    contentDescription =
                        if (ship.status == ShipStatus.Active) "Active" else "Passive"
                }
        )

        Spacer(Modifier.width(18.dp))

        Column {
            Text(
                text = ship.name,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = if (ship.status == ShipStatus.Active) FontWeight.SemiBold else FontWeight.Medium
                ),
                color = if (ship.status == ShipStatus.Passive) onSurfaceVariant else onSurface,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = if (ship.status == ShipStatus.Active) "Active" else "Passive",
                style = MaterialTheme.typography.bodySmall,
                color = onSurfaceVariant
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF6F6F6)
@Composable
private fun HomeScreenPreview() {
    val mock = listOf(
        Ship("Starship-1", ShipStatus.Passive, 1),
        Ship("Dragon-1", ShipStatus.Active, 2),
        Ship("Starship-3", ShipStatus.Active, 3)
    )
    MaterialTheme(colorScheme = lightColorScheme()) {
        HomeScreen(allShips = mock)
    }
}
