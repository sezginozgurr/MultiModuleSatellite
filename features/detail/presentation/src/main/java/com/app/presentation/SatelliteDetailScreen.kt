package com.app.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.app.presentation.SatelliteDetailContract.DetailUiState
import com.app.uikit.theme.boldBlack24
import com.app.uikit.theme.mediumBlack15
import com.app.uikit.theme.regularBlack16Alpha50

@Composable
fun SatelliteDetailScreen(
    uiState: DetailUiState,
    onNavigateBack: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.wrapContentSize()
        ) {
            Text(
                text = "Starship-Bişey text",
                style = boldBlack24
            )

            Text(
                text = "date bişeyler text",
                style = regularBlack16Alpha50,
                modifier = Modifier.padding(top = 4.dp)
            )

            Text(
                text = "Height/Mass: 8383838383 / 28282828",
                style = mediumBlack15,
                modifier = Modifier.padding(top = 32.dp)
            )

            Text(
                text = "Cost: 83.000.000",
                style = mediumBlack15,
                modifier = Modifier.padding(top = 16.dp)
            )

            Text(
                text = "Last Position: (0.86868686,0.6868686886)",
                style = mediumBlack15,
                modifier = Modifier.padding(top = 16.dp)
            )
        }

    }
}