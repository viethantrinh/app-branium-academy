package net.branium.viewmodel

import android.content.Context
import android.os.Parcelable
import android.widget.Toast
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import kotlinx.parcelize.Parcelize
import net.branium.data.model.dto.request.payment.OrderItemRequest
import net.branium.data.model.dto.request.payment.PaymentRequest
import net.branium.data.model.dto.response.base.ResultResponse
import net.branium.data.model.dto.response.payment.PaymentResponse
import net.branium.data.repository.impl.CartRepositoryImpl
import net.branium.data.repository.impl.PaymentRepositoryImpl
import net.branium.data.repository.impl.WishlistRepositoryImpl
import javax.inject.Inject


@HiltViewModel
class PaymentViewModel @Inject constructor(
    private val paymentRepositoryImpl: PaymentRepositoryImpl,
    @ApplicationContext private val context: Context
) :
    ViewModel() {

    private var _apiResponseState = mutableStateOf<ApiResponseState?>(ApiResponseState.Processing)
    val apiResponseState: State<ApiResponseState?> = _apiResponseState

    private val _paymentResponse = mutableStateOf<PaymentResponse?>(null)
    val paymentResponse = _paymentResponse

    @Parcelize
    data class OrderItem(
        val id: Int,
        val title: String,
        val image: String,
        val price: Int,
        val discountPrice: Int
    ) : Parcelable

    fun pay(paymentRequest: PaymentRequest) {
        viewModelScope.launch {
            when (val resultResponse = paymentRepositoryImpl.pay(paymentRequest)) {
                is ResultResponse.Success -> {
                    _paymentResponse.value = resultResponse.data
                    _apiResponseState.value = ApiResponseState.Succeeded
                    _apiResponseState.value?.message = "payment intent requested succeeded!"
                }

                is ResultResponse.Error -> {
                    _apiResponseState.value = ApiResponseState.Failed
                    _apiResponseState.value?.message = resultResponse.exception.message.toString()
                }
            }
        }
    }
}