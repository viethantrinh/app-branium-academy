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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import net.branium.R
import net.branium.data.model.dto.home.Category
import net.branium.data.model.dto.home.PopularCourse
import net.branium.data.model.dto.home.TopPick
import net.branium.ui.screeen.home.CategoryCourseItem
import net.branium.ui.screeen.home.PopularCourseItem
import net.branium.viewmodel.ApiResponseState
import net.branium.viewmodel.HomeViewModel
import androidx.compose.ui.platform.LocalContext


@Composable
fun HomeScreen() {
    val context = LocalContext.current
    val homeViewModel: HomeViewModel = hiltViewModel()
    Column(
        modifier = Modifier.fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

        when (val responseState = homeViewModel.apiResponseState.value) {
            is ApiResponseState.Succeeded -> {
                PopularCourse(courses = homeViewModel.popularCourses.value)
                CategorySources(categories = homeViewModel.categories.value)
                TopPicks(topPicks = homeViewModel.topPicks.value)
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
fun PopularCourse(courses: List<PopularCourse>) {
    val colors = listOf(
        colorResource(id = R.color.color_popular_3),
        colorResource(id = R.color.color_popular_1),
        colorResource(id = R.color.color_popular_2)
    )
    Text(
        modifier = Modifier.padding(start = 14.dp),
        text = "Popular Courses",
        fontSize = 24.sp,
        color = colorResource(id = R.color.primary),
        fontWeight = FontWeight.Bold
    )
    Spacer(modifier = Modifier.height(4.dp))
    LazyRow {
        items(courses) { course ->
            val index = courses.indexOf(course) % colors.size
            val color = colors[index]
            PopularCourseItem(course = course, color)
        }
    }
    Spacer(modifier = Modifier.height(8.dp))
}

@Composable
fun CategorySources(categories: List<Category>) {
    Text(
        modifier = Modifier.padding(start = 14.dp),
        text = "Category",
        fontSize = 24.sp,
        color = colorResource(id = R.color.primary),
        fontWeight = FontWeight.Bold
    )
    Spacer(modifier = Modifier.height(4.dp))
    LazyHorizontalGrid(
        modifier = Modifier
            .padding(start = 14.dp)
            .height(250.dp),
        rows = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(categories) { category ->
            CategoryCourseItem(category = category, {})
        }
    }
    Spacer(modifier = Modifier.height(8.dp))

}

@Composable
fun TopPicks(topPicks: List<TopPick>) {
    Text(
        modifier = Modifier.padding(start = 14.dp),
        text = "Pick For You",
        fontSize = 24.sp,
        color = colorResource(id = R.color.primary),
        fontWeight = FontWeight.Bold
    )
    Spacer(modifier = Modifier.height(4.dp))
    LazyRow(
        modifier = Modifier.padding(start = 14.dp).fillMaxHeight(),
        horizontalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        items(topPicks) { topPick ->
            PickForYouItem(topPick)
        }
    }

}
