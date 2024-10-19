package net.branium.data.model.dto.response

import android.icu.math.BigDecimal

data class CourseResponse(
    val id: Int,
    val title: String,
    val image: String,
    val price: Int,
    val discountPrice: Int
)

data class CartItem(
    val id: Int,
    val title: String,
    val image: String,
    val price: Int,
    val discountPrice: Int
)

data class WishlistItem(
    val id: Int,
    val title: String,
    val image: String,
    val price: Int,
    val discountPrice: Int
)