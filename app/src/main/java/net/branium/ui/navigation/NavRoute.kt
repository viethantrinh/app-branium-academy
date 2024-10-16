package net.branium.ui.navigation

sealed class NavRoute(val route: String) {
    object SplashScreen : NavRoute("splashscreen")
    object SignInScreen : NavRoute("signinscrenn")
    object SignUpScreen : NavRoute("signupscreen")
    object ForgotPasswordScreen: NavRoute("forgotpasswordscreen")
    object CodeResetScreen: NavRoute("coderesetscreen") {
        val resetEmail = "resetEmail"
    }
    object ResetPasswordScreen: NavRoute("resetpasswordscreen")
    object HomeScreen: NavRoute("homescreen")
}