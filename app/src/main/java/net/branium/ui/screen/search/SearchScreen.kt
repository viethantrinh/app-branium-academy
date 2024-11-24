package net.branium.ui.screen.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import net.branium.R
import net.branium.data.model.dto.response.home.Category
import net.branium.viewmodel.HomeViewModel
import net.branium.viewmodel.SearchViewModel

@Composable
fun SearchScreen(
    searchViewModel: SearchViewModel,
    navigationToCoursesOfCategory: (String, String) -> Unit,
    onNavigateToDetailCourse: (Int) -> Unit
) {
    val homeViewModel: HomeViewModel = hiltViewModel()
    val categories = homeViewModel.categories.value
    val searchResult = searchViewModel.coursesSearch

    val isSearching by searchViewModel.isSearching.collectAsState()
    val isFocused by searchViewModel.isFocused.collectAsState()

    val sheetState =
        androidx.compose.material.rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val showBottomSheet by searchViewModel.showBottomSheet.collectAsState()

    ModalBottomSheetLayout(
        sheetContent = {
            if (showBottomSheet) {
                FilterBottomSheet(
                    categories = categories,
                    onApplyFilter = {
                        searchViewModel.hideBottomSheet()
                    }
                )
            }
        },
        sheetState = sheetState
    ) {
        if (isFocused) {
            if (isSearching) {
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }else{
                //search result
                LazyColumn(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                   items(searchResult.value) { course ->
                       SearchItemScreen(course = course) {courseId ->
                           onNavigateToDetailCourse(courseId)
                       }
                   }
                }
            }
        }else{
            ListCategories(
                categories = categories,
                navigationToCoursesOfCategory = navigationToCoursesOfCategory
            )
        }
    }

    // Hiển thị hoặc ẩn BottomSheet
    LaunchedEffect(showBottomSheet) {
        if (showBottomSheet) {
            sheetState.show()
        } else {
            sheetState.hide()
        }
    }

}


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ListCategories(
    categories: List<Category>,
    navigationToCoursesOfCategory: (String, String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        //top search composable
        Box(
            modifier = Modifier
                .wrapContentSize()

        ) {
            Column {
                Text(text = "Top Search", fontSize = 16.sp, fontWeight = FontWeight(600))
                Spacer(modifier = Modifier.height(8.dp))
                FlowRow(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    topCourses.forEach { course ->
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
                                text = course,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }
            }
        }

        //list categories
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 8.dp)
        ) {
            Column {
                Text(text = "Browse Category", fontSize = 16.sp, fontWeight = FontWeight(600))
                Spacer(modifier = Modifier.height(8.dp))
                LazyColumn {
                    items(categories) { category ->
                        CategoryItem(category = category, navigationToCoursesOfCategory)
                    }
                }
            }
        }

    }

}

@Composable
fun CategoryItem(category: Category, navigationToCoursesOfCategory: (String, String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                navigationToCoursesOfCategory(category.id, category.title)
            },
        horizontalArrangement = Arrangement.SpaceBetween

    ) {
        Text(text = category.title, fontSize = 12.sp, fontWeight = FontWeight(400))
        Icon(
            painter = painterResource(id = R.drawable.ic_keyboard_arrow_right_24),
            contentDescription = null
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ListCategoriesPreview() {
    val category = Category("1", "Java", "")
    CategoryItem(category = category) { id, name ->

    }
}

val topCourses = listOf(
    "Java", "Python", "Android", "Flutter", "C++", "C#", "Kotlin", "Swift", "Spring", "Git", "OOP"
)