package net.branium.data.retrofit.service

import net.branium.data.model.dto.request.payment.OrderItemRequest
import net.branium.data.model.dto.request.payment.PaymentRequest
import net.branium.data.model.dto.response.base.ApiResponse
import net.branium.data.model.dto.response.payment.OrderResponse
import net.branium.data.model.dto.response.payment.PaymentResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface PaymentApiService {

    @POST(value = "orders/checkout")
    suspend fun checkout(@Body request: List<OrderItemRequest>): Response<ApiResponse<OrderResponse>>

    @POST(value = "orders/payment")
    suspend fun pay(@Body request: PaymentRequest): Response<ApiResponse<PaymentResponse>>
}