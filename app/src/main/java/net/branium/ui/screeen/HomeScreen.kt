package net.branium.ui.screeen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import net.branium.data.model.dto.homepage.Category
import net.branium.data.model.dto.homepage.PopularCourse
import net.branium.ui.navigation.NavRoute
import net.branium.ui.screeen.home.CustomBottomAppBar
import net.branium.ui.screeen.home.CustomTopAppBar
import net.branium.ui.screeen.home.HomeContainer

@Composable
fun HomeScreen() {

    Scaffold(
        topBar = { CustomTopAppBar() },
        bottomBar = { CustomBottomAppBar({}, {}, {}, {}, {}) } // Đặt CustomBottomAppBar ở vị trí bottomBar
    ) {
        // Nội dung của HomeScreen
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it), // Đảm bảo nội dung không bị che bởi BottomAppBar
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Container")
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
   HomeScreen()
}
