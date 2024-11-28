package net.branium.util

import android.icu.text.NumberFormat
import android.util.Log
import net.branium.ui.screen.course.OptionCourseDetailScreen
import java.util.Locale
import kotlin.math.log

fun formatToVND(amount: Double): String {
    val formatter = NumberFormat.getCurrencyInstance(Locale("vi", "VN"))
    return formatter.format(amount)
}

fun splitDescription(description: String): List<String> {
    return description.split("#")
}

fun getOptionCourseDetail(paid: Boolean, enrolled: Boolean): String {
    if (paid) {
        if (enrolled) {
            return OptionCourseDetailScreen.LearnNowOption.option
        } else {
            return OptionCourseDetailScreen.EnrollOption.option
        }
    } else {
        return OptionCourseDetailScreen.BuyNowOption.option
    }
}

//yyyy-MM-dd
fun getDay(time: String): String {
    if (time.isNotBlank()) {
        val tIndex = time.indexOf('T')
        val date = time.substring(0, tIndex)
        return date;
    }
    return time
}
