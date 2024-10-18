package net.branium.ui.screen.payment

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.stripe.android.PaymentConfiguration
import com.stripe.android.paymentsheet.PaymentSheet
import com.stripe.android.paymentsheet.PaymentSheetResult
import com.stripe.android.paymentsheet.rememberPaymentSheet

@Composable
fun CheckoutScreen() {
    val paymentSheet = rememberPaymentSheet(::onPaymentSheetResult)
    val context = LocalContext.current
    var paymentIntentClientSecret by remember { mutableStateOf<String?>(null) }
    PaymentConfiguration.init(
        context,
        "pk_test_51Q2psLDmtILFtFXB2Eq86fYiljYg7nVkcdtKXUBr04obrVebQPBCJDwhIXVuS29IHLDZlidi9SzrT6HRLg0oKXJi00l1cab1Xk"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 24.dp, end = 24.dp, top = 20.dp, bottom = 46.dp)
    ) {
        // Spacer to push the button to the bottom
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = {
                /* stripe */
                val clientSecret = "pi_3QBJa0DmtILFtFXB1nRLPHWm_secret_a33obOuIE246Uh7JZFzVzR9cF"
                presentPaymentSheet(paymentSheet, clientSecret)
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

private fun presentPaymentSheet(
    paymentSheet: PaymentSheet,
    paymentIntentClientSecret: String
) {
    paymentSheet.presentWithPaymentIntent(paymentIntentClientSecret)
}

private fun onPaymentSheetResult(paymentSheetResult: PaymentSheetResult) {
    when (paymentSheetResult) {
        is PaymentSheetResult.Canceled -> {
            print("Canceled")
        }

        is PaymentSheetResult.Failed -> {
            print("Error: ${paymentSheetResult.error}")
        }

        is PaymentSheetResult.Completed -> {
            // Display for example, an order confirmation screen
            print("Completed")
        }
    }
}