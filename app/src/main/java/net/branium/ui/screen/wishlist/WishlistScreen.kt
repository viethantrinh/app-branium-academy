package net.branium.ui.screen.wishlist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.branium.data.model.dto.response.home.TopPick

@Composable
fun WishlistScreen() {
    val wishlist: List<TopPick> = fakeTopPicks
    Spacer(modifier = Modifier.height(16.dp))
    LazyColumn(
        modifier = Modifier.padding(start = 16.dp, end = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(wishlist) {
            source ->
            WishlistItemScreen(source = source)
        }
    }

}

@Preview(showBackground = true)
@Composable
fun WishlistScreenPreview() {
    WishlistScreen()
}

val fakeTopPicks = listOf(
    TopPick(
        id = 1,
        title = "Smartphone",
        image = "https://example.com/image1.jpg",
        price = 699.99,
        discountPrice = 599.99
    ),
    TopPick(
        id = 2,
        title = "Laptop",
        image = "https://example.com/image2.jpg",
        price = 999.99,
        discountPrice = 899.99
    ),
    TopPick(
        id = 3,
        title = "Headphones",
        image = "https://example.com/image3.jpg",
        price = 199.99,
        discountPrice = 149.99
    ),
    TopPick(
        id = 4,
        title = "Smartwatch",
        image = "https://example.com/image4.jpg",
        price = 299.99,
        discountPrice = 249.99
    ),
    TopPick(
        id = 5,
        title = "Tablet",
        image = "https://example.com/image5.jpg",
        price = 499.99,
        discountPrice = 399.99
    )
)