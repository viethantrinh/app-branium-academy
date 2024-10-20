package net.branium.ui.screeen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.branium.R
import net.branium.data.model.dto.response.home.PopularCourse

//source: Source, navigationToDetail: (Source) -> Unit
@Composable
fun PopularCourseItem(course: PopularCourse, color: Color) {
    Spacer(modifier = Modifier.width(12.dp))
    Column(
        modifier = Modifier
            .padding(4.dp)
            .width(280.dp)
            .height(140.dp)
            .background(
                color = color,
                shape = RoundedCornerShape(8.dp)
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column {
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
                    fontSize = 10.sp,

                    )

                Button(
                    onClick = { /*TODO go to detail course or learn now or enrolled*/ },
                    colors = ButtonDefaults.buttonColors(Color.White)
                ) {

                    Text(
                        text = course.discountPrice.toString(),
                        color = color,
                        fontSize = 12.sp
                    )
                }
            }

        }
    }

}