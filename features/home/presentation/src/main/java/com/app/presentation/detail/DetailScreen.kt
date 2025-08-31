package com.app.presentation.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.uikit.theme.onSurface
import com.app.uikit.theme.onSurfaceVariant
import com.app.uikit.theme.screenBg

data class ShipDetail( //will be delete
    val name: String,
    val launchDateText: String,
    val height: Int,
    val mass: Long,
    val costText: String,
    val lastPosition: Pair<Double, Double>
)

@Composable
fun DetailScreen(
    detail: ShipDetail,
    modifier: Modifier = Modifier
) {
    Surface(color = screenBg, modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    modifier = Modifier.padding(bottom = 16.dp),
                    text = detail.name,
                    style = MaterialTheme.typography.headlineSmall,
                    color = onSurface
                )
                Text(
                    modifier = Modifier.padding(bottom = 66.dp),
                    text = detail.launchDateText,
                    style = MaterialTheme.typography.bodySmall,
                    color = onSurfaceVariant
                )
            }

            LabeledValue(
                modifier = Modifier.padding(bottom = 16.dp),
                label = "Height/Mass",
                value = "${detail.height}/${detail.mass}"
            )

            LabeledValue(
                modifier = Modifier.padding(bottom = 16.dp),
                label = "Cost",
                value = detail.costText
            )

            LabeledValue(
                modifier = Modifier.padding(bottom = 16.dp),
                label = "Last Position",
                value = "(${detail.lastPosition.first}, ${detail.lastPosition.second})"
            )
        }
    }
}

@Composable
private fun LabeledValue(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = buildAnnotatedString {
            withStyle(SpanStyle(fontWeight = FontWeight.SemiBold, color = onSurface)) {
                append("$label: ")
            }
            withStyle(SpanStyle(color = onSurface)) {
                append(value)
            }
        },
        style = MaterialTheme.typography.bodyMedium,
        modifier = modifier
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFF6F6F6)
@Composable
private fun DetailScreenPreview() {
    val mockDetail = ShipDetail(
        name = "Starship-1",
        launchDateText = "01.12.2012",
        height = 110,
        mass = 1_135_000,
        costText = "8.300.000",
        lastPosition = 0.864328541 to 0.646450811
    )
    MaterialTheme(colorScheme = lightColorScheme()) {
        DetailScreen(detail = mockDetail)
    }
}

