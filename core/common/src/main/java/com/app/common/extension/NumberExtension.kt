package com.app.common.extension

import android.annotation.SuppressLint
import java.text.NumberFormat
import java.util.Locale

fun Number?.formatThousands(): String {
    if (this == null) return "N/A"
    val locale = Locale.forLanguageTag("tr-TR")
    val formatter = NumberFormat.getNumberInstance(locale)
    return formatter.format(this)
}

fun Double.trimZeros(maxFractionDigits: Int = 6, locale: Locale = Locale.US): String {
    if (!this.isFinite()) return this.toString()
    val s = String.format(locale, "%.${maxFractionDigits}f", this)
    return if ('.' in s) s.trimEnd('0').trimEnd('.') else s
}