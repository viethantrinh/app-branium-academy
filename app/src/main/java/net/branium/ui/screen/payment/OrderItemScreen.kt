package net.branium.ui.screen.payment

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import net.branium.util.formatToVND
import net.branium.viewmodel.CartViewModel.CartItem
import net.branium.viewmodel.PaymentViewModel
import net.branium.viewmodel.PaymentViewModel.*

@Composable
fun OrderItemScreen(
    orderItem: OrderItem
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Image (Java logo or any other image)
        Image(
            painter = rememberAsyncImagePainter(model = orderItem.image),
            contentDescription = "Course Image",
            modifier = Modifier
                .padding(end = 20.dp, top = 2.dp)
                .size(60.dp)
                .align(Alignment.Top),
            contentScale = ContentScale.FillBounds,
        )


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 8.dp),
        ) {
            Text(
                text = orderItem.title,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = "Viet Han Trinh",
                fontSize = 10.sp,
                color = Color.DarkGray
            )
            Spacer(modifier = Modifier.height(3.dp))

            Row(
                modifier = Modifier.wrapContentSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = formatToVND(orderItem.discountPrice.toDouble()),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 13.sp
                )

                Text(
                    text = formatToVND(orderItem.price.toDouble()),
                    fontWeight = FontWeight.Medium,
                    fontSize = 11.sp,
                    textDecoration = TextDecoration.LineThrough
                )
            }
        }
    }
    Spacer(modifier = Modifier.height(10.dp))
}


@Preview(showBackground = true)
@Composable
fun OrderItemScreenPreview() {
    val orderItem1 = OrderItem(
        id = 11,
        title = "Java programming for beginner",
        image = "https//ngu.com",
        price = 1_000_000,
        discountPrice = 987_000
    )

    OrderItemScreen(orderItem = orderItem1);

}