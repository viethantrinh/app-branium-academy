package net.branium.ui.screen.course

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import net.branium.viewmodel.CourseViewModel

@Composable
fun CourseScreen(onNavigateToDetailCourse: (Int) -> Unit) {

    val courseViewModel: CourseViewModel = hiltViewModel()
    val courses = courseViewModel.listMyCourses.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .width(72.dp)
                .heightIn(28.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFF7F7F7),
                contentColor = Color.Black
            )
        ) {
            Text(
                text = "All",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
        Spacer(
            modifier = Modifier
                .height(20.dp)
                .fillMaxWidth()
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(courses) { course ->
                CourseItem(course = course) { courseId ->
                    onNavigateToDetailCourse(courseId)
                }
            }
        }
    }
}


