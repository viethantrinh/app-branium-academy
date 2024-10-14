package net.branium.ui.navigation.test

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import net.branium.ui.screeen.test.HomeScreen
import net.branium.ui.screeen.test.ProfileScreen
import net.branium.ui.screeen.test.SettingScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = NavRoute.Home.path) {
        addHomeScreen(navController, this)
        addProfileScreen(navController, this)
        addSettingScreen(navController, this);
    }
}

fun addHomeScreen(navController: NavHostController, navGraphBuilder: NavGraphBuilder) {
    navGraphBuilder.composable(
        route = NavRoute.Home.path
    ) {
        HomeScreen(
            onNavigateToProfileScreen = { id, showDetail ->
                navController.navigate(NavRoute.Profile.path.plus("/$id").plus("/$showDetail"))
            },
            onNavigateToSettingScreen = {
                navController.navigate(NavRoute.Setting.path)
            })
    }
}

fun addProfileScreen(navController: NavHostController, navGraphBuilder: NavGraphBuilder) {
    navGraphBuilder.composable(
        route = NavRoute.Profile.path.plus("/{id}/{showDetail}"),
        arguments = listOf(
            navArgument(name = NavRoute.Profile.id, builder = { type = NavType.StringType }),
            navArgument(name = NavRoute.Profile.showDetail, builder = { type = NavType.StringType })
        )
    ) {
        val args = it.arguments
        ProfileScreen(
            id = args?.getString(NavRoute.Profile.id) ?: "no id",
            showDetail = args?.getString(NavRoute.Profile.showDetail) ?: "no detail",
            onNavigateToSettingScreen = { navController.navigate(NavRoute.Setting.path) }
        )
    }
}

fun addSettingScreen(navController: NavHostController, navGraphBuilder: NavGraphBuilder) {
    navGraphBuilder.composable(
        route = NavRoute.Setting.path
    ) {
        SettingScreen(onNavigateToHomeScreen = { navController.navigate(NavRoute.Home.path) })
    }
}

