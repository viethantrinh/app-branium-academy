package net.branium.data.model.dto.request.payment

data class OrderItemRequest(
    val courseId: Int,
    val price: Int,
    val discountPrice: Int
)
