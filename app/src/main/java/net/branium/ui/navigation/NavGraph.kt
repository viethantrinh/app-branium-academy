package net.branium.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import net.branium.ui.screeen.auth.SignInScreen
import net.branium.ui.screeen.auth.SignUpScreen
import net.branium.ui.screeen.auth.SplashScreen

@Composable
fun NavGraph(navController: NavHostController) {
    // define the nav host controller to control navigation of entire application
    NavHost(navController = navController, startDestination = NavRoute.Splash.path) {
        addSplashScreen(navController, this)
        addSignInScreen(navController, this)
        addSignUpScreen(navController, this)
    }
}

private fun addSplashScreen(navController: NavController, navGraphBuilder: NavGraphBuilder) {
    navGraphBuilder.composable(route = NavRoute.Splash.path) {
        SplashScreen(
            onNavigate = {
                // pop out the backstack of splash screen (prevent click return button occurred)
                navController.popBackStack()

                // check if user already have token before or not (from SharePreference)
                // if yes => navigate directly to home screen
                /* TODO: implement token checking here */

                // otherwise => navigate to sign in screen
                /* else( ... ) */
                navController.navigate(NavRoute.SignIn.path)
            })
    }
}

private fun addSignInScreen(navController: NavHostController, navGraphBuilder: NavGraphBuilder) {
    navGraphBuilder.composable(
        route = NavRoute.SignIn.path
    ) {
        SignInScreen(
            onNavigateToForgotPasswordScreen = {},
            onNavigateToSignUpScreen = {
                navController.navigate(NavRoute.SignUp.path)
            },
            onNavigateToHomeScreen = {})
    }
}

private fun addSignUpScreen(navController: NavHostController, navGraphBuilder: NavGraphBuilder) {
    navGraphBuilder.composable(
        route = NavRoute.SignUp.path
    ) {
        SignUpScreen(
            onNavigateToSignInScreen = {
                navController.navigate(NavRoute.SignIn.path)
            }
        )
    }
}
