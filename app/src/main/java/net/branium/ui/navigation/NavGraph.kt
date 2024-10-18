package net.branium.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import net.branium.ui.screen.auth.CodeResetScreen
import net.branium.ui.screen.auth.ForgotPasswordScreen
import net.branium.ui.screen.auth.ResetPasswordScreen
import net.branium.ui.screen.auth.SignInScreen
import net.branium.ui.screen.auth.SignUpScreen
import net.branium.ui.screen.auth.SplashScreen
import net.branium.ui.screen.main.MainScreen

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
        addMainScreen(this)
    }
}

private fun addSplashScreen(navController: NavController, navGraphBuilder: NavGraphBuilder) {
    navGraphBuilder.composable(route = NavRoute.SplashScreen.route) {
        SplashScreen(
            onNavigate = { route ->
                // pop out the backstack of splash screen (prevent click return button occurred)
                navController.popBackStack()
                navController.navigate(route)
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
            onNavigateToMainScreen = {
                navController.navigate(NavRoute.MainScreen.route)
            }
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
                    navController.navigate(NavRoute.ForgotPasswordScreen.route)
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
                    route = NavRoute.SignInScreen.route,
                    navOptions = NavOptions.Builder().setLaunchSingleTop(true)
                        .setRestoreState(true).build()
                )
            }
        )
    }
}

private fun addMainScreen(
    navGraphBuilder: NavGraphBuilder
) {
    navGraphBuilder.composable(
        route = NavRoute.MainScreen.route
    ) {
        MainScreen()
    }
}
