package net.branium.ui.screen.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierInfo
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import net.branium.ui.navigation.NavRoute
import net.branium.ui.navigation.navRouteBottom

@Composable
fun BottomNavScreen(navController: NavController, currentRoute: String?) {
    if (currentRoute != NavRoute.BottomScreen.Home.route
        && currentRoute != NavRoute.BottomScreen.Search.route
        && currentRoute != NavRoute.BottomScreen.Course.route
        && currentRoute != NavRoute.BottomScreen.Wishlist.route
        && currentRoute != NavRoute.BottomScreen.Account.route
    ) {
        return
    }

    BottomNavigation(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        backgroundColor = Color(0xFFF7F7F7),
    ) {
        navRouteBottom.forEach { item ->
            BottomNavigationItem(
                selected = currentRoute == item.bRoute,
                selectedContentColor = Color.Red,
                icon = {
                    Box( // Use Box to align items vertically in the center
                        modifier = Modifier.fillMaxHeight(), // Fill the height of BottomNavigationItem
                        contentAlignment = Alignment.Center // Center the icon vertically
                    ) {
                        Icon(
                            painter = painterResource(id = item.icon),
                            contentDescription = item.bTitle,
                            tint = if (currentRoute == item.bRoute) Color(0xFFF95E0A) else Color(
                                0xFF696C70
                            )
                        )
                    }
                },
                onClick = {
                    navController.navigate(item.bRoute) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}