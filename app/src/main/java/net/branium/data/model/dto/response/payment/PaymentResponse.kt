package net.branium.data.model.dto.response.payment

data class PaymentResponse(
    val clientSecret: String,
    val publishableKey: String
)