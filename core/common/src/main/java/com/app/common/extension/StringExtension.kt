package com.app.common.extension

import java.text.SimpleDateFormat
import java.util.Locale

@Suppress("DEPRECATION")
fun String?.toDisplayDate(pattern: String = "dd.MM.yyyy"): String {
    return try {
        if (isNullOrBlank()) return "N/A"
        val date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(this)
        SimpleDateFormat(pattern, Locale.getDefault()).format(date!!)
    } catch (e: Exception) {
        "N/A"
    }
}