package com.app.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.app.common.extension.formatThousands
import com.app.common.extension.toDisplayDate
import com.app.common.extension.trimZeros
import com.app.presentation.SatelliteDetailContract.DetailUiState
import com.app.resources.R
import com.app.uikit.theme.black
import com.app.uikit.theme.boldBlack24
import com.app.uikit.theme.mediumBlack15
import com.app.uikit.theme.regularBlack16Alpha50
import com.app.uikit.theme.white

@Composable
fun SatelliteDetailScreen(
    uiState: DetailUiState,
    onNavigateBack: () -> Unit,
) {
    val detail = uiState.detail

    val displayDate = detail.firstFlight.toDisplayDate()
    val heightMass = "${detail.height.formatThousands()}/${detail.mass.formatThousands()}"
    val cost = detail.costPerLaunch.formatThousands()
    val lastPosText = uiState.currentPosition
        ?.let { "(${it.posX.trimZeros()},${it.posY.trimZeros()})" }
        ?: ""

    @Composable
    fun LabeledLine(label: String, value: String, topPadding: Dp) {
        Text(
            text = buildAnnotatedString {
                withStyle(SpanStyle(fontWeight = FontWeight.Bold)) { append("$label: ") }
                append(value)
            },
            style = mediumBlack15,
            modifier = Modifier.padding(top = topPadding)
        )
    }

    Box(
        modifier = Modifier
            .background(white)
            .fillMaxSize()
            .padding(horizontal = 24.dp)
    ) {
        IconButton(
            onClick = onNavigateBack,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(top = 16.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.arrow_back),
                contentDescription = "Back",
                tint = black
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.Center)
        ) {
            Text(text = uiState.title, style = boldBlack24)

            Text(
                text = displayDate,
                style = regularBlack16Alpha50,
                modifier = Modifier.padding(top = 4.dp)
            )

            LabeledLine(label = "Height/Mass", value = heightMass, topPadding = 32.dp)
            LabeledLine(label = "Cost", value = cost, topPadding = 16.dp)
            LabeledLine(label = "Last Position", value = lastPosText, topPadding = 16.dp)
        }
    }
}

