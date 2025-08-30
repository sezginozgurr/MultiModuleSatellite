package com.app.common.extension

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.flow.Flow

val <T> Flow<T>.collectWithLifecycle: @Composable (result: (T) -> Unit) -> Unit
    @Composable get() = { function ->
        CollectWithLaunchedEffect {
            function(it)
        }
    }

@Composable
fun <T> Flow<T>.CollectWithLaunchedEffect(
    result: (T) -> Unit,
) {
    LaunchedEffect(Unit) {
        collect { effect ->
            result(effect)
        }
    }
}