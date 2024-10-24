package net.branium.ui.screen.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import net.branium.data.model.dto.response.payment.OrderResponse
import net.branium.ui.navigation.NavRoute
import net.branium.ui.screen.account.AccountScreen
import net.branium.ui.screen.course.CourseDetailScreen
import net.branium.ui.screen.course.CourseScreen
import net.branium.ui.screen.category.CoursesOfCategory
import net.branium.ui.screen.home.HomeScreen
import net.branium.ui.screen.payment.CartScreen
import net.branium.ui.screen.payment.CheckoutScreen
import net.branium.ui.screen.search.SearchScreen
import net.branium.ui.screen.wishlist.WishlistScreen
import net.branium.viewmodel.HomeViewModel

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val homeViewModel: HomeViewModel = hiltViewModel()
    Scaffold(
        topBar = {
            TopBarScreen(
                navController = navController,
                homeViewModel = homeViewModel,
                onNavigateToCartScreen = {
                    navController.navigate(NavRoute.CartScreen.route)
                }
            )
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
            addCartScreen(navController, this, homeViewModel)
            addCheckout(navController, this, homeViewModel)
            addDetailCategoryScreen(navController, this)
            addDetailCourseScreen(navController, this)
        }
    }
}

fun addHomeScreen(navController: NavController, navGraphBuilder: NavGraphBuilder) {
    navGraphBuilder.composable(
        route = NavRoute.BottomScreen.Home.bRoute
    ) {
        HomeScreen(
            onNavigateToDetailCategory = { categoryId, categoryName ->
                navController.navigate(NavRoute.HomeScreen.DetailCategory.hRoute + "/$categoryId/$categoryName")
            },

            onNavigateToDetailCourse = {
            }
        )
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
        CourseScreen(
            onNavigateToDetailCourse = { courseId ->
                navController.navigate(NavRoute.DetailCourseScreen.route + "/$courseId")
            }
        )
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

fun addCartScreen(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
    homeViewModel: HomeViewModel
) {
    navGraphBuilder.composable(
        route = NavRoute.CartScreen.route
    ) {
        CartScreen(
            homeViewModel = homeViewModel,
            onNavigateToCheckOutScreen = { orderResponse ->
                navController.currentBackStackEntry?.savedStateHandle?.set(
                    "orderResponse",
                    orderResponse
                )
                navController.navigate(NavRoute.CheckoutScreen.route)
            }
        )
    }
}

fun addCheckout(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
    homeViewModel: HomeViewModel
) {
    navGraphBuilder.composable(
        route = NavRoute.CheckoutScreen.route
    ) {
        val orderResponse =
            navController.previousBackStackEntry?.savedStateHandle?.get<OrderResponse>("orderResponse")
        if (orderResponse != null) {
            CheckoutScreen(
                orderResponse = orderResponse,
                homeViewModel = homeViewModel,
                onNavigateToCourseScreen = {
                    // Navigate back to the home screen
                    navController.navigate(NavRoute.BottomScreen.Course.bRoute)
                }
            )
        }
    }
}

fun addDetailCategoryScreen(navController: NavController, navGraphBuilder: NavGraphBuilder) {
    navGraphBuilder.composable(
        route = NavRoute.HomeScreen.DetailCategory.hRoute + "/{categoryId}/{categoryName}",
        arguments = listOf(
            navArgument(name = "categoryId") {
                type = NavType.StringType
            },
            navArgument(name = "categoryName") {
                type = NavType.StringType
            }
        )
    ) { backStackEntry ->
        backStackEntry.arguments?.let {
            val categoryId = it.getString("categoryId")
            val categoryName = it.getString("categoryName")
            if (categoryId != null && categoryName != null) {
                CoursesOfCategory(categoryId = categoryId, categoryName = categoryName)
            }
        }
    }
}


fun addDetailCourseScreen(navController: NavController, navGraphBuilder: NavGraphBuilder){
    navGraphBuilder.composable(
        route = NavRoute.DetailCourseScreen.route + "/{courseId}",
        arguments = listOf(
            navArgument(name = "courseId"){
                type = NavType.IntType
            }
        )
    ){
        backStackEntry ->
        backStackEntry.arguments?.let {
            val courseId = it.getInt("courseId")
            CourseDetailScreen(courseId = courseId)
        }
    }
}