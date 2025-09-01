package com.app.common.extension

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.app.common.BadRequestException
import com.app.common.Resource
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

suspend inline fun <T1, T2, R> combineResult(
    restResult1: Resource<T1>,
    restResult2: Resource<T2>,
    crossinline transform: suspend (a: T1, b: T2) -> R
): Resource<R> {
    val list = listOf(restResult1, restResult2)

    return if (list.all { it is Resource.Success }) {
        Resource.Success(
            transform(
                (restResult1 as Resource.Success).data,
                (restResult2 as Resource.Success).data
            )!!
        )
    } else {
        list.filterIsInstance<Resource.Error>()
            .firstOrNull()
            ?: Resource.Error(BadRequestException("Unknown error"))
    }
}