package net.branium.ui.navigation

sealed class NavRoute(val path: String) {
    data object Splash: NavRoute("splash")
    data object SignIn: NavRoute("sign-in")
    data object SignUp: NavRoute("sign-up")
}