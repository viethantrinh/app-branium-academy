package net.branium.data.retrofit.service

import net.branium.data.model.dto.request.payment.OrderItemRequest
import net.branium.data.model.dto.request.payment.OrderStatusUpdateRequest
import net.branium.data.model.dto.request.payment.PaymentRequest
import net.branium.data.model.dto.response.base.ApiResponse
import net.branium.data.model.dto.response.payment.OrderDetailResponse
import net.branium.data.model.dto.response.payment.OrderResponse
import net.branium.data.model.dto.response.payment.PaymentResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface PaymentApiService {

    @POST(value = "orders/checkout")
    suspend fun checkout(@Body request: List<OrderItemRequest>): Response<ApiResponse<OrderResponse>>

    @POST(value = "orders/payment")
    suspend fun pay(@Body request: PaymentRequest): Response<ApiResponse<PaymentResponse>>

    @PUT(value = "orders/{id}/status")
    suspend fun updateOrderStatusAfterPayment(
        @Body orderStatusUpdateRequest: OrderStatusUpdateRequest,
        @Path(value = "id") id: Int
    ): Response<ApiResponse<OrderDetailResponse>>
}