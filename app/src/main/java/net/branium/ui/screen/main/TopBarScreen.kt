package net.branium.ui.screen.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import net.branium.R

@Composable
fun TopBarScreen(
    navController: NavController,
    onNavigateToCart: () -> Unit
) {
    TopAppBar(
        title = {
            Image(
                painter = painterResource(id = R.drawable.image_brand_logo),
                contentDescription = null,
                modifier = Modifier.height(36.dp),
                contentScale = ContentScale.Inside
            )
        },
        actions = {
            Box(modifier = Modifier.padding(end = 16.dp)) {
                IconButton(onClick = { /* Handle cart action ne  */ }) {
                    Icon(
                        imageVector = Icons.Outlined.ShoppingCart,
                        contentDescription = "Cart",
                        tint = Color(0xFF696C70)
                    )
                }

                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(top = 4.dp, end = 4.dp)
                        .background(
                            Color(0xFFF95E0A),
                            shape = CircleShape
                        )
                        .size(16.dp)
                ) {
                    Text(
                        text = "4",
                        color = Color.White,
                        fontSize = 10.sp,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        },
        backgroundColor = Color.White,
        elevation = 5.dp,
        modifier = Modifier.height(72.dp)
    )
}