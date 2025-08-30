package com.app.uikit.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.app.uikit.theme.onSurface
import com.app.uikit.theme.onSurfaceVariant
import com.app.uikit.theme.surface
import com.app.uikit.theme.transparent

@Composable
fun AppSearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "Search",
    height: Dp = 48.dp,
    cornerRadius: Dp = 16.dp
) {
    TextField(
        value = query,
        onValueChange = onQueryChange,
        singleLine = true,
        placeholder = { Text(placeholder, color = onSurfaceVariant) },
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = "Search",
                tint = onSurfaceVariant
            )
        },
        trailingIcon = {
            if (query.isNotEmpty()) {
                IconButton(onClick = { onQueryChange("") }) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = "Clear",
                        tint = onSurfaceVariant
                    )
                }
            }
        },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = { onSearch(query) }),
        shape = RoundedCornerShape(cornerRadius),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = surface,
            unfocusedContainerColor = surface,
            disabledContainerColor = surface,
            focusedIndicatorColor = transparent,
            unfocusedIndicatorColor = transparent,
            cursorColor = onSurface,
            focusedTextColor = onSurface,
            unfocusedTextColor = onSurface
        ),
        modifier = modifier
            .fillMaxWidth()
            .height(height)
    )
}