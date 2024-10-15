package net.branium.test

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun ProfileScreen(id: String, showDetail: String, onNavigateToSettingScreen: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Profile id $id and show detail? $showDetail")
        Button(onClick = onNavigateToSettingScreen) {
            Text(text = "Go to Settings")
        }
    }
}