package net.branium.data.model.dto.response.payment

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import net.branium.data.model.dto.response.course.CourseResponse

@Parcelize
data class OrderDetailResponse(
    val orderId: Int,
    val orderDetails: List<CourseResponse>,
    val totalDiscountPrice: Int,
    val totalPrice: Int,
    val orderStatus: String,
    val createdAt: String,
    val updatedAt: String
) : Parcelable