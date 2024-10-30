package net.branium.ui.screeen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.branium.R
import net.branium.data.model.dto.response.course.CourseDetailResponse
import net.branium.ui.screen.course.OptionCourseDetailScreen
import net.branium.util.formatToVND
import net.branium.util.getOptionCourseDetail

//source: Source, navigationToDetail: (Source) -> Unit
@Composable
fun PopularCourseItem(course: CourseDetailResponse, color: Color, onNavigateToDetailCourse: (Int) -> Unit) {
    val option = getOptionCourseDetail(course.paid, course.enrolled)
    Spacer(modifier = Modifier.width(12.dp))
    Card(
        modifier = Modifier
            .padding(4.dp)
            .width(280.dp)
            .height(140.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(5.dp),
        colors = CardDefaults.cardColors(color)
    ) {
        Column {
            Spacer(modifier = Modifier.heightIn(8.dp))
            Image(
                modifier = Modifier
                    .height(32.dp)
                    .width(70.dp)
                    .padding(top = 4.dp)
                    .padding(start = 16.dp),
                painter = painterResource(id = R.drawable.image_brand_logo),
                contentDescription = "image popular course"
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .padding(top = 4.dp)
                    .padding(end = 16.dp),
                text = course.title,
                color = Color.White,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 16.dp)
                    .padding(end = 16.dp)
                    .padding(bottom = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically

            ) {
                Text(
                    text = course.totalStudents.toString() + " learner",
                    color = Color.White,
                    fontSize = 12.sp,
                )
                Button(
                    onClick = { onNavigateToDetailCourse(course.id)},
                    colors = ButtonDefaults.buttonColors(Color.White),
                    modifier = Modifier.heightIn(18.dp)
                ) {
                    Text(
                        text = "Learn now",
                        color = color,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}