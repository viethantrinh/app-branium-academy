package net.branium.ui.screen.payment

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import net.branium.viewmodel.CartViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CartScreen(
    onNavigateToCheckOutScreen: () -> Unit
) {
    val cartViewModel: CartViewModel = hiltViewModel()
    val cartItems by remember { cartViewModel.cartItems }
    val wishlistItems by remember { cartViewModel.wishlistItems }

    val grouped = listOf<String>("Carts", "Wishlists").groupBy { it[0] }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 24.dp, end = 24.dp, top = 20.dp, bottom = 46.dp)
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
                items(cartItems) {
                    CartItemScreen(
                        cartItem = it,
                        checked = false,
                        onCheckChanged = {},
                        onRemoveClicked = {})
                }
            } else if (it.value[0] == "Wishlists") {
                items(cartItems) { cartItem ->
                    CartItemScreen(
                        cartItem = cartItem,
                        checked = false,
                        onCheckChanged = {

                        },
                        onRemoveClicked = {})
                }
            }
        }

        item {
            Button(
                onClick = { onNavigateToCheckOutScreen() },
                modifier = Modifier
                    .fillMaxWidth(),
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
}


@Composable
@Preview(showBackground = true)
fun CartScreenPreview() {
    CartScreen(onNavigateToCheckOutScreen = {})
}