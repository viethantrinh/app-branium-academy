package net.branium.data.repository.impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import net.branium.data.model.dto.request.payment.OrderItemRequest
import net.branium.data.model.dto.request.payment.OrderStatusUpdateRequest
import net.branium.data.model.dto.request.payment.PaymentRequest
import net.branium.data.model.dto.response.base.ApiResponse
import net.branium.data.model.dto.response.error.ErrorResponse
import net.branium.data.model.dto.response.base.ResultResponse
import net.branium.data.model.dto.response.payment.OrderDetailResponse
import net.branium.data.model.dto.response.payment.OrderResponse
import net.branium.data.model.dto.response.payment.PaymentResponse
import net.branium.data.repository.PaymentRepository
import net.branium.data.retrofit.service.PaymentApiService
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Named

class PaymentRepositoryImpl @Inject
constructor(@Named("RetrofitInstanceWithAuthInterceptor") private val retrofitInstanceWithAuthInterceptor: Retrofit) :
    PaymentRepository {

    private val paymentApiService: PaymentApiService by lazy {
        retrofitInstanceWithAuthInterceptor.create(PaymentApiService::class.java)
    }

    override suspend fun checkout(request: List<OrderItemRequest>): ResultResponse<OrderResponse> {
        return withContext(Dispatchers.IO) {
            val response = paymentApiService.checkout(request)
            if (response.isSuccessful) {
                val responseBody = response.body()!!
                ResultResponse.Success(responseBody.result)
            } else {
                val responseBody = parseCheckoutErrorResponse(response)
                if (responseBody != null) {
                    ResultResponse.Error(Exception(responseBody.message))
                } else {
                    ResultResponse.Error(Exception("checkout failed!"))
                }
            }
        }
    }

    override suspend fun pay(request: PaymentRequest): ResultResponse<PaymentResponse> {
        return withContext(Dispatchers.IO) {
            val response = paymentApiService.pay(request)
            if (response.isSuccessful) {
                val responseBody = response.body()!!
                ResultResponse.Success(responseBody.result)
            } else {
                val responseBody = parsePayErrorResponse(response)
                if (responseBody != null) {
                    ResultResponse.Error(Exception(responseBody.message))
                } else {
                    ResultResponse.Error(Exception("payment intent request failed!"))
                }
            }
        }
    }

    override suspend fun updateOrderStatus(
        request: OrderStatusUpdateRequest,
        orderId: Int
    ): ResultResponse<OrderDetailResponse> {
        return withContext(Dispatchers.IO) {
            val response = paymentApiService.updateOrderStatusAfterPayment(request, orderId)
            if (response.isSuccessful) {
                val responseBody = response.body()!!
                ResultResponse.Success(responseBody.result)
            } else {
                val responseBody = parseUpdateOrderErrorResponse(response)
                if (responseBody != null) {
                    ResultResponse.Error(Exception(responseBody.message))
                } else {
                    ResultResponse.Error(Exception("order status update request failed!"))
                }
            }
        }
    }


    private fun parseCheckoutErrorResponse(response: Response<ApiResponse<OrderResponse>>): ErrorResponse? {
        val converter = retrofitInstanceWithAuthInterceptor
            .responseBodyConverter<ErrorResponse>(ErrorResponse::class.java, arrayOfNulls(0))
        return try {
            response.errorBody()?.let {
                converter.convert(it) // Convert the error body to ErrorResponse
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    private fun parsePayErrorResponse(response: Response<ApiResponse<PaymentResponse>>): ErrorResponse? {
        val converter = retrofitInstanceWithAuthInterceptor
            .responseBodyConverter<ErrorResponse>(ErrorResponse::class.java, arrayOfNulls(0))
        return try {
            response.errorBody()?.let {
                converter.convert(it) // Convert the error body to ErrorResponse
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    private fun parseUpdateOrderErrorResponse(response: Response<ApiResponse<OrderDetailResponse>>): ErrorResponse? {
        val converter = retrofitInstanceWithAuthInterceptor
            .responseBodyConverter<ErrorResponse>(ErrorResponse::class.java, arrayOfNulls(0))
        return try {
            response.errorBody()?.let {
                converter.convert(it) // Convert the error body to ErrorResponse
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
