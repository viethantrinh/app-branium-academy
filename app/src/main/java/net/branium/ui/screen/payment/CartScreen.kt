package net.branium.ui.screen.payment

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CartScreen(
    onNavigateToCheckOutScreen: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 24.dp, end = 24.dp, top = 20.dp, bottom = 46.dp)
    ) {
        // Spacer to push the button to the bottom
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = { onNavigateToCheckOutScreen() },
            modifier = Modifier
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFF95E0A),
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(size = 8.dp)
        ) {
            Text(text = "Check Out")
        }

    }
}