package net.branium.data.model.dto.response.course

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CourseResponse(
    val id: Int,
    val title: String,
    val image: String,
    val price: Int,
    val discountPrice: Int
) : Parcelable

