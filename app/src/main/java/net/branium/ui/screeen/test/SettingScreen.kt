package net.branium.ui.screeen.test

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@Composable
fun SettingScreen(
    onNavigateToHomeScreen: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Setting screen", fontSize = 40.sp)
        Button(onClick = onNavigateToHomeScreen) {
            Text(text = "Navigate to home")
        }
    }
}