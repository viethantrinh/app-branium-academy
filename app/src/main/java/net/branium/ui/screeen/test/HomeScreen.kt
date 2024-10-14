package net.branium.ui.screeen.test

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun HomeScreen(
    onNavigateToProfileScreen: (id: String, showDetail: String) -> Unit,
    onNavigateToSettingScreen: () -> Unit
) {

    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Button(onClick = { onNavigateToProfileScreen("77", "ngu") }) {
            Text(text = "Profile")
        }

        Button(onClick = onNavigateToSettingScreen) {
            Text(text = "Settings")
        }
    }
}