package net.branium.ui.screen.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.delay
import net.branium.R
import net.branium.data.model.dto.request.auth.IntrospectRequest
import net.branium.di.TokenManager
import net.branium.ui.navigation.NavRoute
import net.branium.viewmodel.SplashScreenViewModel
import net.branium.viewmodel.SplashScreenViewModel.AuthState


@Composable
fun SplashScreen(
    onNavigate: (route: String) -> Unit
) {
    val context = LocalContext.current
    val splashScreenViewModel: SplashScreenViewModel = hiltViewModel()

    val tokenManager = TokenManager(context)
    val token = tokenManager.getToken()

    if (token.isNullOrEmpty()) {
        onNavigate(NavRoute.SignInScreen.route)
    } else {
        val request = IntrospectRequest(token = token.toString())
        splashScreenViewModel.introspectToken(request)
    }

    LaunchedEffect(key1 = splashScreenViewModel.authState) {
        delay(100)
        when (splashScreenViewModel.authState.value) {
            is AuthState.Authenticated -> {
                onNavigate(NavRoute.MainScreen.route)
            }

            is AuthState.UnAuthenticated -> {
                onNavigate(NavRoute.SignInScreen.route)
            }

            else -> {
                onNavigate(NavRoute.SignInScreen.route)
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.image_brand_logo),
            contentDescription = "app's logo",
            contentScale = ContentScale.Fit
        )
    }
}

