package net.branium.ui.screen.home

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import net.branium.R
import net.branium.data.model.dto.response.home.Category
import net.branium.data.model.dto.response.home.PopularCourse
import net.branium.data.model.dto.response.home.TopPick
import net.branium.ui.screeen.home.CategoryCourseItem
import net.branium.ui.screeen.home.PopularCourseItem
import net.branium.viewmodel.ApiResponseState
import net.branium.viewmodel.HomeViewModel
import androidx.compose.ui.platform.LocalContext
import net.branium.data.model.dto.response.course.CourseDetailResponse
import net.branium.viewmodel.CourseViewModel


@Composable
fun HomeScreen(onNavigateToDetailCategory: (String, String) -> Unit, onNavigateToDetailCourse: (Int) -> Unit) {
    val context = LocalContext.current
    val homeViewModel: HomeViewModel = hiltViewModel()
    val courseViewModel: CourseViewModel = hiltViewModel()
    courseViewModel.getListPopularCourseDetails(homeViewModel.popularCourses.value)
    LaunchedEffect(Unit) {
        courseViewModel.getListPopularCourseDetails(homeViewModel.popularCourses.value)
    }
    Column(
        modifier = Modifier.fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

        when (val responseState = homeViewModel.apiResponseState.value) {
            is ApiResponseState.Succeeded -> {
                PopularCourse(courses = courseViewModel.listPopularCourseDetails.value){courseId ->
                    onNavigateToDetailCourse(courseId)
                }
                CategorySources(categories = homeViewModel.categories.value){ categoryId, categoryName ->
                    onNavigateToDetailCategory(categoryId, categoryName)
                }
                TopPicks(topPicks = homeViewModel.topPicks.value){courseId ->
                    onNavigateToDetailCourse(courseId)
                }
            }

            is ApiResponseState.Failed -> {
                Toast.makeText(context, responseState.message, Toast.LENGTH_SHORT).show()
            }

            is ApiResponseState.Processing -> {
                Toast.makeText(context, responseState.message, Toast.LENGTH_SHORT).show()
            }

            else -> Unit
        }
    }

}

@Composable
fun PopularCourse(courses: List<CourseDetailResponse>, onNavigateToDetailCourse: (Int) -> Unit) {
    val colors = listOf(
        colorResource(id = R.color.color_popular_3),
        colorResource(id = R.color.color_popular_1),
        colorResource(id = R.color.color_popular_2)
    )
    Spacer(modifier = Modifier.height(16.dp))
    Text(
        modifier = Modifier.padding(start = 14.dp),
        text = "Popular Courses",
        fontSize = 24.sp,
        color = colorResource(id = R.color.primary),
        fontWeight = FontWeight.Bold
    )
    Spacer(modifier = Modifier.height(8.dp))
    LazyRow {
        items(courses) { course ->
            val index = courses.indexOf(course) % colors.size
            val color = colors[index]
            PopularCourseItem(course = course, color, onNavigateToDetailCourse)
        }
    }

}

@Composable
fun CategorySources(categories: List<Category>, navigationToCoursesOfCategory: (String, String) -> Unit) {
    Spacer(modifier = Modifier.height(16.dp))
    Text(
        modifier = Modifier.padding(start = 14.dp),
        text = "Category",
        fontSize = 24.sp,
        color = colorResource(id = R.color.primary),
        fontWeight = FontWeight.Bold
    )
    Spacer(modifier = Modifier.height(12.dp))
    LazyHorizontalGrid(
        modifier = Modifier
            .padding(start = 14.dp)
            .height(250.dp),
        rows = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        items(categories) { category ->
            CategoryCourseItem(category = category, navigationToCoursesOfCategory)
        }
    }


}

@Composable
fun TopPicks(topPicks: List<TopPick>, onNavigateToDetailCourse: (Int) -> Unit) {
    Spacer(modifier = Modifier.height(8.dp))
    Text(
        modifier = Modifier.padding(start = 14.dp),
        text = "Pick For You",
        fontSize = 24.sp,
        color = colorResource(id = R.color.primary),
        fontWeight = FontWeight.Bold
    )
    Spacer(modifier = Modifier.height(8.dp))
    LazyRow(
        modifier = Modifier.padding(start = 14.dp).fillMaxHeight(),
        horizontalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        items(topPicks) { topPick ->
            PickForYouItem(topPick, onNavigateToDetailCourse)
        }
    }

}
