package net.branium.ui.screen.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import net.branium.R
import net.branium.di.CartQuantityManager
import net.branium.ui.navigation.NavRoute
import net.branium.viewmodel.HomeViewModel

@Composable
fun TopBarScreen(
    navController: NavController,
    homeViewModel: HomeViewModel,
    onNavigateToCartScreen: () -> Unit
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    when (currentRoute) {
        NavRoute.BottomScreen.Home.bRoute -> {
            TopBar(
                title = {
                    Image(
                        painter = painterResource(id = R.drawable.image_brand_logo),
                        contentDescription = null,
                        modifier = Modifier.height(36.dp),
                        contentScale = ContentScale.Inside
                    )
                },
                showCartQuantityScreen = true,
                onNavigateToCartScreen = onNavigateToCartScreen,
                homeViewModel = homeViewModel
            )
        }

        NavRoute.BottomScreen.Search.bRoute -> {
            TopBar(
                title = {
                    /* TODO: Search Screen sẽ có top bar là một thanh tìm kiếm */
                },
                showCartQuantityScreen = true,
                onNavigateToCartScreen = onNavigateToCartScreen,
                homeViewModel = homeViewModel
            )
        }

        NavRoute.BottomScreen.Course.bRoute -> {
            TopBar(
                title = {
                    Text(
                        text = "My Courses",
                        color = Color(0xFFF95E0A),
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        lineHeight = 28.sp
                    )
                },
                showCartQuantityScreen = true,
                onNavigateToCartScreen = onNavigateToCartScreen,
                homeViewModel = homeViewModel
            )
        }

        NavRoute.BottomScreen.Wishlist.bRoute -> {
            TopBar(
                title = {
                    Text(
                        text = "Wishlist",
                        color = Color(0xFFF95E0A),
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        lineHeight = 28.sp
                    )
                },
                showCartQuantityScreen = true,
                onNavigateToCartScreen = onNavigateToCartScreen,
                homeViewModel = homeViewModel
            )
        }

        NavRoute.BottomScreen.Account.bRoute -> {
            return
        }

        NavRoute.CartScreen.route -> {
            TopBar(
                title = {
                    Row(
                        horizontalArrangement = Arrangement.Absolute.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            onClick = {
                                navController.navigate(NavRoute.BottomScreen.Home.bRoute)
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowLeft,
                                contentDescription = null,
                                modifier = Modifier
                                    .size(32.dp)
                            )
                        }
                        Text(
                            text = "Cart",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                },
                showCartQuantityScreen = false,
                onNavigateToCartScreen = onNavigateToCartScreen,
                homeViewModel = homeViewModel
            )
        }

        NavRoute.CheckoutScreen.route -> {
            TopBar(
                title = {
                    Row(
                        horizontalArrangement = Arrangement.Absolute.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            onClick = {
                                navController.navigateUp()
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowLeft,
                                contentDescription = null,
                                modifier = Modifier
                                    .size(32.dp)
                            )
                        }
                        Text(
                            text = "Checkout",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                },
                showCartQuantityScreen = false,
                onNavigateToCartScreen = onNavigateToCartScreen,
                homeViewModel = homeViewModel
            )
        }

        NavRoute.HomeScreen.DetailCategory.route + "/{categoryId}/{categoryName}"-> {
            TopBar(
                title = {
                    Row(
                        horizontalArrangement = Arrangement.Absolute.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            onClick = {
                                navController.navigateUp()
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowLeft,
                                contentDescription = null,
                                modifier = Modifier
                                    .size(32.dp)
                            )
                        }
                        Text(
                            text = "Category",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                },
                showCartQuantityScreen = true,
                onNavigateToCartScreen = onNavigateToCartScreen,
                homeViewModel = homeViewModel

            )
        }
        NavRoute.DetailCourseScreen.route + "/{courseId}"-> {
            TopBar(
                title = {
                    Row(
                        horizontalArrangement = Arrangement.Absolute.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            onClick = {
                                navController.navigateUp()
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowLeft,
                                contentDescription = null,
                                modifier = Modifier
                                    .size(32.dp)
                            )
                        }
                    }
                },
                showCartQuantityScreen = true,
                onNavigateToCartScreen = onNavigateToCartScreen,
                homeViewModel = homeViewModel
            )
        }
    }

}

@Composable
fun TopBar(
    title: @Composable () -> Unit,
    showCartQuantityScreen: Boolean = true,
    homeViewModel: HomeViewModel,
    onNavigateToCartScreen: () -> Unit = {}
) {
    TopAppBar(
        title = {
            /* tùy theo screen mà thay đổi tiêu đề => dùng navController */
            title()
        },
        actions = {
            if (showCartQuantityScreen) {
                CartQuantityScreen(
                    onNavigateToCartScreen = onNavigateToCartScreen,
                    homeViewModel = homeViewModel
                )
            }
        },
        backgroundColor = Color.White,
        elevation = 5.dp,
        modifier = Modifier.height(72.dp)
    )
}

@Composable
fun CartQuantityScreen(
    homeViewModel: HomeViewModel,
    onNavigateToCartScreen: () -> Unit
) {
    val cartQuantity by remember { homeViewModel.cartQuantity }
    Box(modifier = Modifier.padding(end = 16.dp)) {
        IconButton(onClick = {
            onNavigateToCartScreen()
        }) {
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
            /* TODO: replace with cart quantities */
            Text(
                text = "$cartQuantity",
                color = Color.White,
                fontSize = 10.sp,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}