package net.branium.util

import android.icu.text.NumberFormat
import java.util.Locale

fun formatToVND(amount: Double): String {
    val formatter = NumberFormat.getCurrencyInstance(Locale("vi", "VN"))
    return formatter.format(amount)
}

fun splitDescription(description: String): List<String> {
    return description.split("#")
}