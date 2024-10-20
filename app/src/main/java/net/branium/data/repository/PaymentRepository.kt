package net.branium.data.repository

import net.branium.data.model.dto.request.payment.OrderItemRequest
import net.branium.data.model.dto.request.payment.PaymentRequest
import net.branium.data.model.dto.response.base.ResultResponse
import net.branium.data.model.dto.response.payment.OrderResponse
import net.branium.data.model.dto.response.payment.PaymentResponse

interface PaymentRepository {
    suspend fun checkout(request: List<OrderItemRequest>): ResultResponse<OrderResponse>
    suspend fun pay(request: PaymentRequest): ResultResponse<PaymentResponse>
}