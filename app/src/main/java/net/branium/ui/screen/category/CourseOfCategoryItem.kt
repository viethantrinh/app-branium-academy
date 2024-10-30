package net.branium.ui.screen.category

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import net.branium.R
import net.branium.data.model.dto.response.course.CourseResponse
import net.branium.util.formatToVND

@Composable
fun CourseOfCategoryItem(course: CourseResponse, onNavigateToDetailCourse: (Int) -> Unit) {
    Column(
        modifier = Modifier
            .width(382.dp)
            .height(200.dp)
            .clickable {
                onNavigateToDetailCourse(course.id)
            }
    ) {
        Box(
            modifier = Modifier
                .width(382.dp)
                .height(136.dp)
                .border(
                    width = 0.5.dp,
                    color = colorResource(id = R.color.hint_color),
                )
        ) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = rememberAsyncImagePainter(course.image),
                contentDescription = null,
                contentScale = ContentScale.FillWidth
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .width(382.dp)
                .height(56.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(20.dp)
                        .wrapContentHeight(Alignment.CenterVertically),
                    text = course.title,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(16.dp)
                        .wrapContentHeight(Alignment.CenterVertically),
                    text = "Phuong Anh",
                    fontSize = 8.sp,
                    color = colorResource(id = R.color.color_author)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(20.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        modifier = Modifier
                            .padding(end = 4.dp)
                            .fillMaxHeight()
                            .wrapContentHeight(Alignment.CenterVertically),
                        text = formatToVND(course.price.toDouble()),
                        fontSize = 10.sp,
                        textDecoration = TextDecoration.LineThrough
                    )
                    Text(
                        modifier = Modifier
                            .fillMaxHeight()
                            .padding(start = 4.dp)
                            .wrapContentHeight(Alignment.CenterVertically),
                        text = formatToVND(course.price.toDouble()),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }


    }
}

@Preview(showBackground = true)
@Composable
fun CourseOfCategoryItemPreview() {
    val course = CourseResponse(1, "Android 14 App Developer Bootcamp 2024", "", 1500000, 1000000)
    CourseOfCategoryItem(course, {})
}