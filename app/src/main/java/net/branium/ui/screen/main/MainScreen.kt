package net.branium.ui.screen.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import net.branium.ui.navigation.NavRoute
import net.branium.ui.screen.account.AccountScreen
import net.branium.ui.screen.course.CourseScreen
import net.branium.ui.screen.home.HomeScreen
import net.branium.ui.screen.search.SearchScreen
import net.branium.ui.screen.wishlist.WishlistScreen

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        topBar = {
            TopBarScreen(navController = navController, onNavigateToCart = {})
        },
        bottomBar = {
            BottomNavScreen(navController = navController, currentRoute = currentRoute)
        },
    ) {
        NavHost(
            navController = navController,
            startDestination = NavRoute.BottomScreen.Home.bRoute,
            modifier = Modifier.padding(it)
        ) {
            addHomeScreen(navController, this)
            addSearchScreen(navController, this)
            addCourseScreen(navController, this)
            addWishlistScreen(navController, this)
            addAccountScreen(navController, this)
        }
    }
}

fun addHomeScreen(navController: NavController, navGraphBuilder: NavGraphBuilder) {
    navGraphBuilder.composable(
        route = NavRoute.BottomScreen.Home.bRoute
    ) {
        HomeScreen()
    }
}

fun addSearchScreen(navController: NavController, navGraphBuilder: NavGraphBuilder) {
    navGraphBuilder.composable(
        route = NavRoute.BottomScreen.Search.bRoute
    ) {
        SearchScreen()
    }
}

fun addCourseScreen(navController: NavController, navGraphBuilder: NavGraphBuilder) {
    navGraphBuilder.composable(
        route = NavRoute.BottomScreen.Course.bRoute
    ) {
        CourseScreen()
    }
}

fun addWishlistScreen(navController: NavController, navGraphBuilder: NavGraphBuilder) {
    navGraphBuilder.composable(
        route = NavRoute.BottomScreen.Wishlist.bRoute
    ) {
        WishlistScreen()
    }
}

fun addAccountScreen(navController: NavController, navGraphBuilder: NavGraphBuilder) {
    navGraphBuilder.composable(
        route = NavRoute.BottomScreen.Account.bRoute
    ) {
        AccountScreen()
    }
}