package net.branium.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import net.branium.ui.screeen.auth.ForgotPasswordScreen
import net.branium.ui.screeen.auth.SignInScreen
import net.branium.ui.screeen.auth.SignUpScreen
import net.branium.ui.screeen.auth.SplashScreen

@Composable
fun NavGraph(navController: NavHostController) {
    // define the nav host controller to control navigation of entire application
    NavHost(navController = navController, startDestination = NavRoute.SplashScreen.route) {
        addSplashScreen(navController, this)
        addSignInScreen(navController, this)
        addSignUpScreen(navController, this)
        addForgotPasswordScreen(navController, this)
    }
}

private fun addSplashScreen(navController: NavController, navGraphBuilder: NavGraphBuilder) {
    navGraphBuilder.composable(route = NavRoute.SplashScreen.route) {
        SplashScreen(
            onNavigate = {
                // pop out the backstack of splash screen (prevent click return button occurred)
                navController.popBackStack()

                // check if user already have token before or not (from SharePreference)
                // if yes => navigate directly to home screen
                /* TODO: implement token checking here */

                // otherwise => navigate to sign in screen
                /* else( ... ) */
                navController.navigate(NavRoute.SignInScreen.route)
            })
    }
}

private fun addSignInScreen(navController: NavController, navGraphBuilder: NavGraphBuilder) {
    navGraphBuilder.composable(
        route = NavRoute.SignInScreen.route
    ) {
        SignInScreen(
            onNavigateToForgotPasswordScreen = {
                navController.navigate(NavRoute.ForgotPasswordScreen.route)
            },
            onNavigateToSignUpScreen = {
                navController.navigate(NavRoute.SignUpScreen.route)
            },
            onNavigateToHomeScreen = {}
        )
    }
}

private fun addSignUpScreen(navController: NavController, navGraphBuilder: NavGraphBuilder) {
    navGraphBuilder.composable(
        route = NavRoute.SignUpScreen.route
    ) {
        SignUpScreen(
            onNavigateToSignInScreen = {
                navController.popBackStack()
                navController.navigate(NavRoute.SignInScreen.route)
            }
        )
    }
}

private fun addForgotPasswordScreen(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder
) {
    navGraphBuilder.composable(
        route = NavRoute.ForgotPasswordScreen.route
    ) {
        ForgotPasswordScreen(
            onNavigateBackToSignInScreen = {
                navController.popBackStack()
                navController.navigate(NavRoute.SignInScreen.route)
            },
            onNavigateToCodeScreen = {}
        )
    }
}
