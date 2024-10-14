package net.branium.ui.navigation.test

sealed class NavRoute(val path: String) {
    object Home: NavRoute("home")
    object Profile: NavRoute("profile") {
        val id = "id"
        val showDetail = "showDetail"
    }
    object Setting: NavRoute("setting")
}
