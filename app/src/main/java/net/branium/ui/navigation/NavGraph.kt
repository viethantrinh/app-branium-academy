package net.branium.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import kotlinx.coroutines.delay
import net.branium.ui.screeen.auth.CodeResetScreen
import net.branium.ui.screeen.auth.ForgotPasswordScreen
import net.branium.ui.screeen.auth.ResetPasswordScreen
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
        addCodeResetScreen(navController, this)
        addResetPasswordScreen(navController, this)
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
                navController.navigateUp()
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
                navController.navigateUp()
            },
            onNavigateToCodeResetScreen = { resetEmail ->
                navController.currentBackStackEntry?.savedStateHandle?.set("resetEmail", resetEmail)
                navController.navigate(NavRoute.CodeResetScreen.route)
            }
        )
    }
}

private fun addCodeResetScreen(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder
) {
    navGraphBuilder.composable(
        route = NavRoute.CodeResetScreen.route
    ) {
        val resetEmail =
            navController.previousBackStackEntry?.savedStateHandle?.get<String>("resetEmail")

        if (!resetEmail.isNullOrBlank()) {
            CodeResetScreen(
                resetEmail = resetEmail,
                onNavigateBackToForgotPwdScreen = {
                    navController.navigateUp()
                },
                onNavigateToResetPasswordScreen = { code, email ->
                    navController.currentBackStackEntry?.savedStateHandle?.set("code", code)
                    navController.currentBackStackEntry?.savedStateHandle?.set("email", email)
                    navController.navigate(NavRoute.ResetPasswordScreen.route)
                })
        }
    }
}

private fun addResetPasswordScreen(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder
) {
    navGraphBuilder.composable(
        route = NavRoute.ResetPasswordScreen.route
    ) {
        val code = navController.previousBackStackEntry?.savedStateHandle?.get<String>("code")
        val email = navController.previousBackStackEntry?.savedStateHandle?.get<String>("email")
        ResetPasswordScreen(
            code = code,
            email = email,
            onNavigateToSignInScreen = {
                navController.popBackStack()
                navController.navigate(
                    route =  NavRoute.SignInScreen.route,
                    navOptions = NavOptions.Builder().setLaunchSingleTop(true)
                        .setRestoreState(true).build()
                )
            }
        )
    }
}
