package net.branium.ui.screen.payment

import android.icu.math.BigDecimal
import android.icu.text.NumberFormat
import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import net.branium.data.model.dto.response.CartItem
import net.branium.viewmodel.CartViewModel
import java.util.Locale

@Composable
fun CartItemScreen(
    cartItem: CartItem,
    checked: Boolean = false,
    onCheckChanged: (Boolean) -> Unit,
    onRemoveClicked: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Checkbox
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckChanged,
            colors = CheckboxDefaults.colors(
                checkedColor = Color(0xFFEA4B0C)
            ),
            modifier = Modifier
                .align(Alignment.Top)
                .padding(top = 7.dp)
        )

        // Image (Java logo or any other image)
        Image(
            painter = rememberAsyncImagePainter(model = cartItem.image),
            contentDescription = "Course Image",
            modifier = Modifier
                .padding(end = 8.dp, top = 2.dp)
                .size(52.dp)
                .align(Alignment.Top),
            contentScale = ContentScale.FillBounds,
        )


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 8.dp),
        ) {
            Text(
                text = cartItem.title,
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
                    text = formatToVND(cartItem.discountPrice.toDouble()),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 13.sp
                )

                Text(
                    text = formatToVND(cartItem.price.toDouble()),
                    fontWeight = FontWeight.Medium,
                    fontSize = 11.sp,
                    textDecoration = TextDecoration.LineThrough
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = { onRemoveClicked() },
                modifier = Modifier
                    .height(26.dp)
                    .align(Alignment.End),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFF95E0A), // Use the same orange color
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(20), // Rounded corners
                contentPadding = PaddingValues(
                    horizontal = 16.dp,
                    vertical = 4.dp
                ) // Adjust padding to make it more compact
            ) {
                Text(
                    text = "Remove from cart",
                    fontSize = 12.sp // Smaller font size for compact appearance
                )
            }
        }
    }
    Spacer(modifier = Modifier.height(10.dp))
}

fun formatToVND(amount: Double): String {
    val formatter = NumberFormat.getCurrencyInstance(Locale("vi", "VN"))
    return formatter.format(amount)
}

@Preview(showBackground = true)
@Composable
fun CartItemScreenPreview() {
    val cartItem1 = CartItem(
        id = 11,
        title = "Java programming for beginner",
        image = "https//ngu.com",
        price = 1_000_000,
        discountPrice = 987_000
    )

    CartItemScreen(cartItem = cartItem1, checked = true, onCheckChanged = {}, onRemoveClicked = {});

}