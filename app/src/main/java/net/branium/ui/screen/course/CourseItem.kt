package net.branium.ui.screen.course

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import net.branium.R
import net.branium.data.model.dto.response.course.CourseResponse

@Composable
fun CourseItem(course: CourseResponse, onNavigateToDetailCourse: (Int) -> Unit) {
    Row(
        modifier = Modifier
            .width(382.dp)
            .height(60.dp).clickable {
                onNavigateToDetailCourse(course.id)
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = course.image),
            contentDescription = null,
            modifier = Modifier
                .width(60.dp)
                .height(60.dp),
            contentScale = ContentScale.Crop,
            alignment = Alignment.Center
        )
        Spacer(
            modifier = Modifier
                .width(8.dp)
                .fillMaxHeight()
        )
        Column(
            modifier = Modifier
                .width(220.dp)
                .height(52.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = course.title,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = "Thu Uyen",
                fontSize = 8.sp,
                color = colorResource(id = R.color.hint_color)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                androidx.compose.material3.Text(
                    modifier = Modifier.padding(end = 4.dp),
                    text = "5.0",
                    color = colorResource(id = R.color.yellow),
                    fontSize = 8.sp
                )
                for (i in 1..5) {
                    Image(
                        modifier = Modifier
                            .height(16.dp)
                            .width(16.dp)
                            .padding(1.dp),
                        painter = painterResource(id = R.drawable.icon_star_rate_24),
                        contentDescription = "star rate",
                        colorFilter = ColorFilter.tint(colorResource(id = R.color.yellow))
                    )
                }
            }

        }
        Spacer(
            modifier = Modifier
                .width(8.dp)
                .fillMaxHeight()
        )
        Text(
            text = "40% complete",
            fontSize = 12.sp,
            color = colorResource(id = R.color.primary_600),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.fillMaxWidth()
        )

    }
}


@Preview(showBackground = true)
@Composable
fun CourseItemPreview() {
    val course = CourseResponse(1, "Java core vip ahaaha", "", 1000000, 900000)
    CourseItem(course, {})
}