package net.branium.viewmodel

import android.content.Context
import android.icu.math.BigDecimal
import android.widget.Toast
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import net.branium.data.model.dto.response.CartItem
import net.branium.data.model.dto.response.CourseResponse
import net.branium.data.model.dto.response.ResultResponse
import net.branium.data.model.dto.response.WishlistItem
import net.branium.data.repository.impl.CartRepositoryImpl
import net.branium.data.repository.impl.WishlistRepositoryImpl
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartRepositoryImpl: CartRepositoryImpl,
    private val withListRepositoryImpl: WishlistRepositoryImpl,
    @ApplicationContext private val context: Context
) :
    ViewModel() {

    private var _cartItems = mutableStateOf(listOf<CartItem>())
    val cartItems = _cartItems

    private var _wishlistItems = mutableStateOf(listOf<WishlistItem>())
    val wishlistItems = _wishlistItems

    init {
        fetchAllCartItems()
        fetchAllWishlistItems()
    }

    fun fetchAllCartItems() {
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

    fun fetchAllWishlistItems() {
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
}