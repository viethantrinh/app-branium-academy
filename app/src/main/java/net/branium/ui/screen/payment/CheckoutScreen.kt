package net.branium.ui.screen.payment

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.stripe.android.PaymentConfiguration
import com.stripe.android.payments.paymentlauncher.PaymentResult
import com.stripe.android.paymentsheet.PaymentSheet
import com.stripe.android.paymentsheet.PaymentSheetResult
import com.stripe.android.paymentsheet.rememberPaymentSheet
import net.branium.R
import net.branium.data.model.dto.request.payment.OrderStatusUpdateRequest
import net.branium.data.model.dto.request.payment.PaymentRequest
import net.branium.data.model.dto.response.payment.OrderResponse
import net.branium.util.formatToVND
import net.branium.viewmodel.ApiResponseState
import net.branium.viewmodel.HomeViewModel
import net.branium.viewmodel.PaymentViewModel
import net.branium.viewmodel.PaymentViewModel.*

@Composable
fun CheckoutScreen(
    orderResponse: OrderResponse,
    homeViewModel: HomeViewModel,
    onNavigateToCourseScreen: () -> Unit
) {
    val context = LocalContext.current
    val paymentViewModel: PaymentViewModel = hiltViewModel()
    val paymentSheet = rememberPaymentSheet {
        onPaymentSheetResult(
            it,
            onNavigateToCourseScreen,
            paymentViewModel,
            homeViewModel,
            orderResponse
        )
    }

    LaunchedEffect(key1 = paymentViewModel.paymentResponse.value) {
        when (paymentViewModel.apiResponseState.value) {
            is ApiResponseState.Succeeded -> {
                val clientSecret = paymentViewModel.paymentResponse.value?.clientSecret!!
                val publishableKey = paymentViewModel.paymentResponse.value?.publishableKey!!
                PaymentConfiguration.init(context, publishableKey)
                presentPaymentSheet(paymentSheet, clientSecret)
                Toast.makeText(
                    context,
                    paymentViewModel.apiResponseState.value?.message,
                    Toast.LENGTH_SHORT
                ).show()
            }

            is ApiResponseState.Failed -> {
                Toast.makeText(
                    context,
                    paymentViewModel.apiResponseState.value?.message,
                    Toast.LENGTH_SHORT
                ).show()
            }

            else -> Unit
        }
    }

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
                    bottom = 46.dp
                )
        ) {
            item {
                Text(
                    text = "Order Items",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFFF95E0A)
                )
                Spacer(modifier = Modifier.height(16.dp))
            }


            items(orderResponse.orderDetails.map {
                OrderItem(
                    id = it.id,
                    title = it.title,
                    image = it.image,
                    price = it.price,
                    discountPrice = it.discountPrice
                )
            }) {
                OrderItemScreen(orderItem = it)
            }

        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 40.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Total:",
                    fontSize = 18.sp,
                    color = Color.DarkGray,
                    fontWeight = FontWeight.SemiBold
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = formatToVND(orderResponse.totalDiscountPrice.toDouble()),
                        fontSize = 18.sp,
                        color = Color.DarkGray,
                        fontWeight = FontWeight.SemiBold
                    )

                    Text(
                        text = formatToVND(orderResponse.totalPrice.toDouble()),
                        fontSize = 16.sp,
                        color = Color.Gray,
                        fontWeight = FontWeight.SemiBold,
                        textDecoration = TextDecoration.LineThrough
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 40.dp)
                    .border(
                        BorderStroke(width = 1.dp, color = Color.Gray),
                        shape = RoundedCornerShape(8.dp)
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(
                    text = "Payment:",
                    fontSize = 14.sp,
                    color = Color.DarkGray,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(start = 10.dp, end = 10.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.image_stripe_logo),
                    contentDescription = "PayPal Logo",
                    modifier = Modifier
                        .padding(end = 10.dp)
                        .size(width = 45.dp, height = 20.dp),
                    contentScale = ContentScale.FillBounds
                )
            }

            Spacer(modifier = Modifier.heightIn(4.dp))

            // Pay button that stays at the bottom
            Button(
                onClick = {
                    val paymentRequest = PaymentRequest(orderResponse.orderId)
                    paymentViewModel.pay(paymentRequest)
                },
                modifier = Modifier
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFF95E0A),
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(size = 8.dp)
            ) {
                Text(text = "Pay")
            }
        }
    }
}

private fun presentPaymentSheet(
    paymentSheet: PaymentSheet,
    paymentIntentClientSecret: String
) {
    paymentSheet.presentWithPaymentIntent(paymentIntentClientSecret)
}

private fun onPaymentSheetResult(
    paymentSheetResult: PaymentSheetResult,
    onNavigateToCourseScreen: () -> Unit,
    paymentViewModel: PaymentViewModel,
    homeViewModel: HomeViewModel,
    orderResponse: OrderResponse
) {
    when (paymentSheetResult) {
        is PaymentSheetResult.Canceled -> {
            print("Canceled")
            val request = OrderStatusUpdateRequest(status = "canceled")
            paymentViewModel.updateOrderStatus(request, orderResponse.orderId)
            onNavigateToCourseScreen()
        }

        is PaymentSheetResult.Failed -> {
            print("Error: ${paymentSheetResult.error}")
            val request = OrderStatusUpdateRequest(status = "failed")
            paymentViewModel.updateOrderStatus(request, orderResponse.orderId)
            onNavigateToCourseScreen()
        }

        is PaymentSheetResult.Completed -> {
            print("Succeeded")
            val request = OrderStatusUpdateRequest(status = "succeeded")
            paymentViewModel.updateOrderStatus(request, orderResponse.orderId)
            homeViewModel.deleteCartQuantityAfterPaymentSuccess(
                orderResponse.orderDetails.count()
            )
            onNavigateToCourseScreen()
        }
    }
}