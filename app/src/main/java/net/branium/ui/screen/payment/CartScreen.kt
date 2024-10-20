package net.branium.ui.screen.payment

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import net.branium.data.model.dto.response.base.ApiResponse
import net.branium.data.model.dto.response.payment.OrderResponse
import net.branium.viewmodel.ApiResponseState
import net.branium.viewmodel.CartViewModel
import net.branium.viewmodel.CartViewModel.CartItem

@Composable
fun CartScreen(
    onNavigateToCheckOutScreen: (orderResponse: OrderResponse) -> Unit
) {
    val grouped = listOf<String>("Carts", "Wishlists").groupBy { it[0] }
    val cartViewModel: CartViewModel = hiltViewModel()
    val cartItems by remember { cartViewModel.cartItems }
    val wishlistItems by remember { cartViewModel.wishlistItems }
    // A map to keep track of the checked state of each cart item
    val checkedStateMap = remember { mutableStateMapOf<CartItem, Boolean>() }

    val context = LocalContext.current

    // Initialize the checked state map for all cart items (if needed)
    LaunchedEffect(cartItems) {
        cartItems.forEach { item ->
            if (!checkedStateMap.containsKey(item)) {
                checkedStateMap[item] = false  // Default to unchecked
            }
        }
    }

    LaunchedEffect(key1 = cartViewModel.orderResponse.value) {
        when (cartViewModel.apiResponseState.value) {
            is ApiResponseState.Succeeded -> {
                onNavigateToCheckOutScreen(cartViewModel.orderResponse.value!!)
                Toast.makeText(
                    context,
                    cartViewModel.apiResponseState.value?.message,
                    Toast.LENGTH_SHORT
                ).show()
            }

            is ApiResponseState.Failed -> {
                Toast.makeText(
                    context,
                    cartViewModel.apiResponseState.value?.message,
                    Toast.LENGTH_SHORT
                ).show()
            }

            else -> Unit
        }
    }

    // Function to retrieve all checked cart items
    fun getCheckedCartItems(): List<CartItem> {
        return cartItems.filter { cartItem ->
            checkedStateMap[cartItem] == true  // Filter only the items that are checked
        }
    }


    // Use Box to layer the LazyColumn and the Button
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = 24.dp,
                    end = 24.dp,
                    top = 20.dp,
                    bottom = 60.dp
                ) // Padding for bottom space for the button
        ) {
            grouped.forEach {
                item {
                    Text(
                        text = it.value[0],
                        fontSize = 24.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFFF95E0A)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }

                if (it.value[0] == "Carts") {
                    items(cartItems) { cartItem ->
                        CartItemScreen(
                            cartItem = cartItem,
                            checked = checkedStateMap[cartItem]
                                ?: false, // Use the map to track checked state
                            onCheckChanged = { isChecked ->
                                checkedStateMap[cartItem] =
                                    isChecked  // Update the checked state when checkbox is clicked
                            },
                            onRemoveClicked = {
                                cartViewModel.removeItemFromCart(cartItem)
                            }
                        )
                    }
                } else if (it.value[0] == "Wishlists") {
                    items(wishlistItems) { wishlistItem ->
                        WishlistItemScreen(
                            wishlistItem = wishlistItem,
                            onAddToCartClicked = {
                                cartViewModel.addWishlistItemToCart(wishlistItem)
                            },
                            onRemoveClicked = {
                                cartViewModel.removeItemFromWishlist(wishlistItem)
                            }
                        )
                    }
                }
            }
        }

        // Checkout button that stays at the bottom
        Button(
            onClick = {
                cartViewModel.checkout(getCheckedCartItems())
            },
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)  // Aligns the button at the bottom center of the screen
                .padding(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFF95E0A),
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(size = 8.dp)
        ) {
            Text(text = "Check Out")
        }
    }
}


@Composable
@Preview(showBackground = true)
fun CartScreenPreview() {
    CartScreen(onNavigateToCheckOutScreen = {})
}