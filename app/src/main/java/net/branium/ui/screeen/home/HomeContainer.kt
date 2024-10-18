package net.branium.ui.screeen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.branium.R
import net.branium.data.model.dto.homepage.Category
import net.branium.data.model.dto.homepage.PopularCourse

@Composable
fun HomeContainer(courses: List<PopularCourse>, categories: List<Category>) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            modifier = Modifier.padding(start = 16.dp),
            text = "Popular Courses",
            fontSize = 24.sp,
            color = colorResource(id = R.color.primary),
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        PopularCourse(courses = courses)
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            modifier = Modifier.padding(start = 16.dp),
            text = "Category",
            fontSize = 24.sp,
            color = colorResource(id = R.color.primary),
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        CategorySources(categories = categories)

    }
}

@Composable
fun PopularCourse(courses: List<PopularCourse>) {
    val colors = listOf(
        colorResource(id = R.color.color_popular_3),
        colorResource(id = R.color.color_popular_1),
        colorResource(id = R.color.color_popular_2)
    )
    LazyRow {
        items(courses) { course ->
            val index = courses.indexOf(course) % colors.size
            val color = colors[index]
            PopularCourseItem(course = course, color)
        }
    }
}




@Composable
fun CategorySources(categories: List<Category>) {
    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        modifier = Modifier.wrapContentSize()
    ) {
        items(categories) { category ->
            CategoryCourseItem(category = category, {})
        }
    }

}


@Composable
fun PickForYou() {

}


@Preview(showBackground = true)
@Composable
fun HomeContainerPreview() {

    val popularCourse1 = PopularCourse(
        1,
        "Cấu trúc dữ liệu và Giải thuật",
        "https://be-branium-academy-ad1581e47cac.herokuapp.com/branium-academy/api/v1/resources/images/3IY3X2sK",
        1799000.00,
        729000.00,
        190
    )
    val popularCourse2 = PopularCourse(
        2,
        "OPP lập trình từ cơ bản đến nâng cao",
        "https://be-branium-academy-ad1581e47cac.herokuapp.com/branium-academy/api/v1/resources/images/3IY3X2sK",
        9000.00,
        7200.00,
        20
    )
    val popularCourse3 = PopularCourse(
        3,
        "Android Java Kotlin chi tiết 2024",
        "https://be-branium-academy-ad1581e47cac.herokuapp.com/branium-academy/api/v1/resources/images/3IY3X2sK",
        400000.00,
        200000.00,
        560
    )

    val popularCourses = listOf(popularCourse1, popularCourse2, popularCourse3)
    //PopularCourseItem(course)
//    PopularCourse(courses = courses)
    val category1 = Category(
        "1",
        "Java1",
        "https://be-branium-academy-ad1581e47cac.herokuapp.com/branium-academy/api/v1/resources/images/FR12KyLv"
    )

    val category2 = Category(
        "1",
        "Java2",
        "https://be-branium-academy-ad1581e47cac.herokuapp.com/branium-academy/api/v1/resources/images/FR12KyLv"
    )

    val category3 = Category(
        "1",
        "Java3",
        "https://be-branium-academy-ad1581e47cac.herokuapp.com/branium-academy/api/v1/resources/images/FR12KyLv"
    )

    val category4 = Category(
        "1",
        "Java4",
        "https://be-branium-academy-ad1581e47cac.herokuapp.com/branium-academy/api/v1/resources/images/FR12KyLv"
    )

    val category5 = Category(
        "1",
        "Java5",
        "https://be-branium-academy-ad1581e47cac.herokuapp.com/branium-academy/api/v1/resources/images/FR12KyLv"
    )


    val category6 = Category(
        "1",
        "Java6",
        "https://be-branium-academy-ad1581e47cac.herokuapp.com/branium-academy/api/v1/resources/images/FR12KyLv"
    )

    val category7 = Category(
        "1",
        "Java7",
        "https://be-branium-academy-ad1581e47cac.herokuapp.com/branium-academy/api/v1/resources/images/FR12KyLv"
    )

    val category8 = Category(
        "1",
        "Java8",
        "https://be-branium-academy-ad1581e47cac.herokuapp.com/branium-academy/api/v1/resources/images/FR12KyLv"
    )

    val category9 = Category(
        "1",
        "Java9",
        "https://be-branium-academy-ad1581e47cac.herokuapp.com/branium-academy/api/v1/resources/images/FR12KyLv"
    )
    val category10 = Category(
        "1",
        "Java0",
        "https://be-branium-academy-ad1581e47cac.herokuapp.com/branium-academy/api/v1/resources/images/FR12KyLv"
    )
    val categories = listOf(
        category1, category2, category3,
        category4, category5, category6, category7, category8, category9, category10
    )
//    CategoryCourseItem(category1, {})
//    CategorySources(categories)
    HomeContainer(courses = popularCourses, categories = categories)
}