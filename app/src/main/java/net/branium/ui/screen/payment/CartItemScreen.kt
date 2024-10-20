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

@Composable
fun CartItemScreen(
    cartItem: CartItem,
    checked: Boolean = false,
    onCheckChanged: (Boolean) -> Unit,
    onRemoveClicked: () -> Unit
) {
    // State to show or hide the confirmation dialog
    var showDialog by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Checkbox
        Checkbox(
            checked = checked,
            onCheckedChange = { isChecked ->
                onCheckChanged(isChecked)
            },
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
                onClick = { showDialog = true },
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

    if (showDialog) {
        AlertDialog(
            containerColor = Color.White,
            onDismissRequest = { showDialog = false },
            title = { Text(text = "Remove Item") },
            text = { Text("Are you sure you want to remove this item from the cart?") },
            confirmButton = {
                Button(
                    onClick = {
                        onRemoveClicked()  // Call remove action
                        showDialog = false  // Hide dialog after confirmation
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFF95E0A),
                        contentColor = Color.White
                    )
                ) {
                    Text("Yes", fontWeight = FontWeight.SemiBold)
                }
            },
            dismissButton = {
                Button(
                    onClick = { showDialog = false },  // Just hide dialog if "No" clicked
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFF95E0A),
                        contentColor = Color.White
                    )
                ) {
                    Text("No", fontWeight = FontWeight.SemiBold)
                }
            }
        )
    }

    Spacer(modifier = Modifier.height(10.dp))
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