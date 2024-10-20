package net.branium.ui.screen.payment

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import net.branium.util.formatToVND
import net.branium.viewmodel.CartViewModel.WishlistItem


@Composable
fun WishlistItemScreen(
    wishlistItem: WishlistItem,
    onAddToCartClicked: () -> Unit,
    onRemoveClicked: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Image (Java logo or any other image)
        Image(
            painter = rememberAsyncImagePainter(model = wishlistItem.image),
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
                text = wishlistItem.title,
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
                    text = formatToVND(wishlistItem.discountPrice.toDouble()),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 13.sp
                )

                Text(
                    text = formatToVND(wishlistItem.price.toDouble()),
                    fontWeight = FontWeight.Medium,
                    fontSize = 11.sp,
                    textDecoration = TextDecoration.LineThrough
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.wrapContentSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(
                    onClick = { onAddToCartClicked() },
                    modifier = Modifier
                        .widthIn(min = 100.dp)
                        .height(26.dp),
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
                        text = "Add to cart",
                        fontSize = 12.sp // Smaller font size for compact appearance
                    )
                }
                Button(
                    onClick = { onRemoveClicked() },
                    modifier = Modifier
                        .height(26.dp)
                        .widthIn(min = 100.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White, // Use the same orange color
                        contentColor = Color.Black,
                    ),
                    border = BorderStroke(width = 1.dp, color = Color.Black),
                    shape = RoundedCornerShape(20), // Rounded corners
                    contentPadding = PaddingValues(
                        horizontal = 16.dp,
                        vertical = 4.dp
                    ) // Adjust padding to make it more compact
                ) {
                    Text(
                        text = "Remove",
                        fontSize = 12.sp // Smaller font size for compact appearance
                    )
                }
            }
        }
    }
    Spacer(modifier = Modifier.height(24.dp))
}