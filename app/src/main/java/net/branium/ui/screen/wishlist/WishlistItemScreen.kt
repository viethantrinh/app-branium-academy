package net.branium.ui.screen.wishlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import net.branium.R
import net.branium.data.model.dto.response.home.TopPick

@Composable
fun WishlistItemScreen(source: TopPick) {
    Row(
        modifier = Modifier
            .height(60.dp)
            .width(385.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter = rememberAsyncImagePainter(source.image),
            contentDescription = null,
            modifier = Modifier.size(60.dp)
        )
        Box(
            modifier = Modifier
                .width(282.dp)
                .height(56.dp)
                .padding(start = 8.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize().padding(end = 12.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = source.title,
                    fontSize = 14.sp,
                    lineHeight = 24.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "Trinh Han",
                    fontSize = 8.sp,
                    lineHeight = 16.sp,
                    style = TextStyle(color = colorResource(id = R.color.color_author)),
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = source.discountPrice.toString() + "đ",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(end = 4.dp)
                    )
                    Text(
                        text = source.price.toString() + "đ",
                        fontSize = 10.sp,
                        style = TextStyle(color = colorResource(id = R.color.color_author)),
                        modifier = Modifier.padding(start = 4.dp),
                        textDecoration = TextDecoration.LineThrough
                    )

                }
            }
        }
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier
                    .height(20.dp)
                    .width(20.dp),
                painter = painterResource(id = R.drawable.icon_favorite_24),
                contentDescription = null,
                colorFilter = ColorFilter.tint(colorResource(id = R.color.color_wishlist_favorite))

            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WishlistItemScreenPreview() {
    val source = TopPick(1, "Docker and AWS for Java Developer", "", 1500000.0, 900000.0)
    WishlistItemScreen(source)
}