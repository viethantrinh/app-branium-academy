package net.branium.ui.screen.course

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import net.branium.R
import net.branium.data.model.dto.response.course.CourseResponse
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
        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .width(72.dp)
                .height(28.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(colorResource(id = R.color.color_button_all))

        ) {
            Text(
                text = "All",
                fontSize = 14.sp,
                fontWeight = FontWeight(500)
            )
        }
        Spacer(
            modifier = Modifier
                .height(32.dp)
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

@Preview(showBackground = true)
@Composable
fun CourseScreenPreview() {
    CourseScreen({})
}

