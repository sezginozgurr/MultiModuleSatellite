package com.app.common.extension

import java.text.NumberFormat
import java.util.Locale

fun Number?.formatThousands(): String {
    if (this == null) return "N/A"
    val locale = Locale.forLanguageTag("tr-TR")
    val formatter = NumberFormat.getNumberInstance(locale)
    return formatter.format(this)
}