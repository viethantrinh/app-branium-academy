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
import net.branium.data.model.dto.response.base.ResultResponse
import net.branium.data.model.dto.response.payment.OrderResponse
import net.branium.data.repository.impl.CartRepositoryImpl
import net.branium.data.repository.impl.PaymentRepositoryImpl
import net.branium.data.repository.impl.WishlistRepositoryImpl
import javax.inject.Inject


@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartRepositoryImpl: CartRepositoryImpl,
    private val withListRepositoryImpl: WishlistRepositoryImpl,
    private val paymentRepositoryImpl: PaymentRepositoryImpl,
    @ApplicationContext private val context: Context
) :
    ViewModel() {

    @Parcelize
    data class CartItem(
        val id: Int,
        val title: String,
        val image: String,
        val price: Int,
        val discountPrice: Int
    ) : Parcelable

    @Parcelize
    data class WishlistItem(
        val id: Int,
        val title: String,
        val image: String,
        val price: Int,
        val discountPrice: Int
    ) : Parcelable

    private var _cartItems = mutableStateOf(listOf<CartItem>())
    val cartItems: State<List<CartItem>> = _cartItems

    private var _wishlistItems = mutableStateOf(listOf<WishlistItem>())
    val wishlistItems: State<List<WishlistItem>> = _wishlistItems

    private var _orderResponse = mutableStateOf<OrderResponse?>(null)
    val orderResponse: State<OrderResponse?> = _orderResponse

    private var _apiResponseState = mutableStateOf<ApiResponseState?>(ApiResponseState.Processing)
    val apiResponseState: State<ApiResponseState?> = _apiResponseState


    init {
        fetchAllCartItems()
        fetchAllWishlistItems()
    }

    private fun fetchAllCartItems() {
        viewModelScope.launch {
            when (val resultResponse = cartRepositoryImpl.listCartItems()) {
                is ResultResponse.Success -> {
                    val courses = resultResponse.data!!
                    _cartItems.value = courses.map {
                        CartItem(
                            id = it.id,
                            title = it.title,
                            image = it.image,
                            price = it.price,
                            discountPrice = it.discountPrice
                        )
                    }
                }

                is ResultResponse.Error -> {
                    Toast.makeText(
                        context,
                        resultResponse.exception.message.toString(),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    private fun fetchAllWishlistItems() {
        viewModelScope.launch {
            when (val resultResponse = withListRepositoryImpl.listWishlistItems()) {
                is ResultResponse.Success -> {
                    val courses = resultResponse.data!!
                    _wishlistItems.value = courses.map {
                        WishlistItem(
                            id = it.id,
                            title = it.title,
                            image = it.image,
                            price = it.price,
                            discountPrice = it.discountPrice
                        )
                    }
                }

                is ResultResponse.Error -> {
                    Toast.makeText(
                        context,
                        resultResponse.exception.message.toString(),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    fun removeItemFromCart(cartItem: CartItem, homeViewModel: HomeViewModel) {
        viewModelScope.launch {
            when (val resultResponse = cartRepositoryImpl.removeCartItem(cartItem.id)) {
                is ResultResponse.Success -> {
                    val courses = resultResponse.data!!
                    _cartItems.value = courses.map {
                        CartItem(
                            id = it.id,
                            title = it.title,
                            image = it.image,
                            price = it.price,
                            discountPrice = it.discountPrice
                        )
                    }
                    homeViewModel.updateCartQuantity(newQuantity = _cartItems.value.count())
                }

                is ResultResponse.Error -> {
                    Toast.makeText(
                        context,
                        resultResponse.exception.message.toString(),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    fun removeItemFromWishlist(wishlistItem: WishlistItem) {
        viewModelScope.launch {
            when (val resultResponse = withListRepositoryImpl.removeWishlistItem(wishlistItem.id)) {
                is ResultResponse.Success -> {
                    val courses = resultResponse.data!!
                    _wishlistItems.value = courses.map {
                        WishlistItem(
                            id = it.id,
                            title = it.title,
                            image = it.image,
                            price = it.price,
                            discountPrice = it.discountPrice
                        )
                    }
                }

                is ResultResponse.Error -> {
                    Toast.makeText(
                        context,
                        resultResponse.exception.message.toString(),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    fun addWishlistItemToCart(wishlistItem: WishlistItem, homeViewModel: HomeViewModel) {
        viewModelScope.launch {
            when (val resultResponse = cartRepositoryImpl.addCartItem(wishlistItem.id)) {
                is ResultResponse.Success -> {
                    val courses = resultResponse.data!!
                    _cartItems.value = courses.map {
                        CartItem(
                            id = it.id,
                            title = it.title,
                            image = it.image,
                            price = it.price,
                            discountPrice = it.discountPrice
                        )
                    }
                    _wishlistItems.value -= wishlistItem
                    homeViewModel.updateCartQuantity(newQuantity = _cartItems.value.count())
                }

                is ResultResponse.Error -> {
                    Toast.makeText(
                        context,
                        resultResponse.exception.message.toString(),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    fun checkout(cartItems: List<CartItem>) {
        viewModelScope.launch {
            val request = cartItems.map {
                OrderItemRequest(
                    courseId = it.id,
                    price = it.price,
                    discountPrice = it.discountPrice
                )
            }

            when (val resultResponse = paymentRepositoryImpl.checkout(request)) {
                is ResultResponse.Success -> {
                    _orderResponse.value = resultResponse.data
                    _apiResponseState.value = ApiResponseState.Succeeded
                    _apiResponseState.value?.message = "checkout succeeded!"
                }

                is ResultResponse.Error -> {
                    _apiResponseState.value = ApiResponseState.Failed
                    _apiResponseState.value?.message = resultResponse.exception.message.toString()
                }
            }
        }
    }
}