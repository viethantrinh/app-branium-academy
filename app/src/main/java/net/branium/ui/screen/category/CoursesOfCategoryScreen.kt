package net.branium.ui.screen.category

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import net.branium.R
import net.branium.viewmodel.ApiResponseState
import net.branium.viewmodel.CategoryViewModel

@Composable
fun TitleDetailCategory() {
    Row(
        modifier = Modifier.fillMaxHeight(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.icon_arrow_back_ios_24),
            contentDescription = null
        )
        Text(
            text = "Category",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun CoursesOfCategory(categoryId: String, categoryName: String) {
    val categoryViewModel: CategoryViewModel = hiltViewModel()
    val context = LocalContext.current

    // Gọi hàm lấy dữ liệu khi categoryId thay đổi
    LaunchedEffect(categoryId) {
        categoryViewModel.getAllCoursesByCategoryId(categoryId)
    }

    val courses = categoryViewModel.coursesByCategoryId.value

    when (val responseState = categoryViewModel.apiResponseState.value) {
        is ApiResponseState.Succeeded -> {
            Column(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp)
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Courses with $categoryName",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.primary_600)
                )
                Spacer(modifier = Modifier.height(16.dp))
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(courses) { course ->
                        CourseOfCategoryItem(course = course)
                    }
                }
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
