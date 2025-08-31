package com.app.presentation

import android.widget.Toast
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
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.common.extension.collectWithLifecycle
import com.app.domain.model.SatelliteListUiModel
import com.app.presentation.HomeContract.HomeUiAction
import com.app.presentation.HomeContract.HomeUiEffect
import com.app.presentation.HomeContract.HomeUiState
import com.app.uikit.components.AppSearchBar
import com.app.uikit.theme.divider
import com.app.uikit.theme.mediumBlack16
import com.app.uikit.theme.regularBlack12Alpha50
import com.app.uikit.theme.regularBlack14Alpha50
import com.app.uikit.theme.regularBlack16Alpha50
import com.app.uikit.theme.screenBg
import com.app.uikit.theme.statusGreen
import com.app.uikit.theme.statusRed
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
internal fun HomeScreenRoute(
    uiState: HomeUiState,
    uiEffect: Flow<HomeUiEffect>,
    onAction: (HomeUiAction) -> Unit,
    onShipClick: (Int) -> Unit = {},
) {
    val context = LocalContext.current

    uiEffect.collectWithLifecycle { effect ->
        when (effect) {
            is HomeUiEffect.NavigateToDetails -> onShipClick(effect.id)
            is HomeUiEffect.ShowToast -> Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
        }
    }

    HomeScreen(
        uiState = uiState,
        onAction = onAction
    )
}

@Composable
private fun HomeScreen(
    uiState: HomeUiState,
    onAction: (HomeUiAction) -> Unit
) {
    Surface(color = screenBg, modifier = Modifier.fillMaxSize()) {
        Box(Modifier.fillMaxSize()) {

            Column(Modifier.fillMaxSize()) {
                Spacer(Modifier.height(8.dp))

                AppSearchBar(
                    query = uiState.query,
                    onQueryChange = { onAction(HomeUiAction.QueryChanged(it)) },
                    onSearch = {},
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                Spacer(Modifier.height(8.dp))

                val filtered =
                    if (uiState.query.isBlank()) uiState.satellites
                    else uiState.satellites.filter { it.name.contains(uiState.query, ignoreCase = true) }

                when {
                    uiState.error != null -> {
                        Column(
                            Modifier
                                .fillMaxSize()
                                .padding(top = 32.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Top
                        ) {
                            Text(uiState.error, style = regularBlack14Alpha50)
                            Spacer(Modifier.height(8.dp))
                            Button(onClick = { onAction(HomeUiAction.Retry) }) { Text("Retry") }
                        }
                    }

                    filtered.isEmpty() -> {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(top = 32.dp),
                            contentAlignment = Alignment.TopCenter
                        ) {
                            Text("No results", style = regularBlack14Alpha50)
                        }
                    }

                    else -> {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            contentPadding = PaddingValues(top = 12.dp, bottom = 24.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            itemsIndexed(
                                items = filtered,
                                key = { _, item -> item.id }
                            ) { index, item ->
                                SatelliteRow(
                                    item = item,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable { onAction(HomeUiAction.ItemClicked(item.id)) }
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

            if (uiState.isLoading) {
                Box(
                    Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background.copy(alpha = 0.2f)),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}

@Composable
private fun SatelliteRow(
    item: SatelliteListUiModel,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier.padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        val dotColor = if (item.active) statusGreen else statusRed
        Box(
            modifier = Modifier
                .size(16.dp)
                .clip(CircleShape)
                .background(dotColor)
                .semantics(mergeDescendants = true) {
                    contentDescription = if (item.active) "Active" else "Passive"
                }
        )

        Spacer(Modifier.width(18.dp))

        Column {
            Text(
                text = item.name,
                style = if (item.active) mediumBlack16 else regularBlack16Alpha50,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = if (item.active) "Active" else "Passive",
                style = regularBlack12Alpha50
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF6F6F6)
@Composable
private fun HomeScreenPreviewWithData() {
    val mock = HomeUiState(
        isLoading = false,
        query = "",
        satellites = listOf(
            SatelliteListUiModel(id = 1, active = false, name = "Starship-1"),
            SatelliteListUiModel(id = 2, active = true,  name = "Dragon-1"),
            SatelliteListUiModel(id = 3, active = true,  name = "Starship-3")
        ),
        error = null
    )

    MaterialTheme(colorScheme = lightColorScheme()) {
        HomeScreenRoute(
            uiState = mock,
            uiEffect = emptyFlow(),
            onAction = {},
            onShipClick = {}
        )
    }
}

@Preview(name = "Loading", showBackground = true, backgroundColor = 0xFFF6F6F6)
@Composable
private fun HomeScreenPreviewLoading() {
    val mock = HomeUiState(
        isLoading = true,
        query = "",
        satellites = emptyList(),
        error = null
    )

    MaterialTheme(colorScheme = lightColorScheme()) {
        HomeScreenRoute(
            uiState = mock,
            uiEffect = emptyFlow(),
            onAction = {},
            onShipClick = {}
        )
    }
}

@Preview(name = "Error", showBackground = true, backgroundColor = 0xFFF6F6F6)
@Composable
private fun HomeScreenPreviewError() {
    val mock = HomeUiState(
        isLoading = false,
        query = "",
        satellites = emptyList(),
        error = "Something went wrong"
    )

    MaterialTheme(colorScheme = lightColorScheme()) {
        HomeScreenRoute(
            uiState = mock,
            uiEffect = emptyFlow(),
            onAction = {},
            onShipClick = {}
        )
    }
}
