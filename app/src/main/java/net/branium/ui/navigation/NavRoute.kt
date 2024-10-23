package net.branium.ui.navigation

import androidx.annotation.DrawableRes
import net.branium.R

sealed class NavRoute(val route: String) {
    object SplashScreen : NavRoute("splash_screen")
    object SignInScreen : NavRoute("sign_in_screen")
    object SignUpScreen : NavRoute("sign_up_screen")
    object ForgotPasswordScreen : NavRoute("forgot_password_screen")
    object CodeResetScreen : NavRoute("code_reset_screen") {
        val resetEmail = "resetEmail"
    }

    object ResetPasswordScreen : NavRoute("reset_password_screen")
    object MainScreen : NavRoute("main_screen")

    sealed class BottomScreen(val bRoute: String, val bTitle: String, @DrawableRes val icon: Int) :
        NavRoute(route = bRoute) {
        object Home : BottomScreen("home_screen", "Home", R.drawable.icon_nav_home_24)
        object Search : BottomScreen("search_screen", "Search", R.drawable.icon_nav_search_24)
        object Course : BottomScreen("course_screen", "Course", R.drawable.icon_nav_course_24)
        object Wishlist :
            BottomScreen("wishlist_screen", "Wishlist", R.drawable.icon_nav_wish_list_24)

        object Account : BottomScreen("account_screen", "Account", R.drawable.icon_nav_account_24)
    }

    sealed class HomeScreen(val hRoute: String, val hTitle: String) : NavRoute(route = hRoute) {
        object DetailCategory : HomeScreen("detail_category_screen", "DetailCategory")
        object DetailPopularCourse : HomeScreen("detail_popular_course_screen", "DetailPopularCourse")
        object DetailTopPick : HomeScreen("detail_top_pick_screen", "DetailTopPick")
    }

    object CartScreen: NavRoute("cart_screen")
    object CheckoutScreen: NavRoute("checkout_screen")
    object DetailCourseScreen: NavRoute("detail_course_screen")
}

val navRouteBottom = listOf(
    NavRoute.BottomScreen.Home,
    NavRoute.BottomScreen.Search,
    NavRoute.BottomScreen.Course,
    NavRoute.BottomScreen.Wishlist,
    NavRoute.BottomScreen.Account,
)

val navRouteHome = listOf(
    NavRoute.HomeScreen.DetailCategory,
    NavRoute.HomeScreen.DetailPopularCourse,
    NavRoute.HomeScreen.DetailTopPick,
)