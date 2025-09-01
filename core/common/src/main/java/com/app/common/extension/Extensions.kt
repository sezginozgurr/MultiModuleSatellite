package com.app.common.extension

fun Int?.orZero() = this ?: 0

fun Double?.orZero() = this ?: 0.0

fun Boolean?.orFalse() = this ?: false