package net.branium.ui.screeen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import net.branium.R
import net.branium.data.model.dto.homepage.Category

@Composable
fun CategoryCourseItem(category: Category, navigationToCourses: (Category) -> Unit) {
    Column(
        modifier = Modifier
            .wrapContentSize()
            .padding(8.dp)
            .clickable {
                navigationToCourses(category) //nav to courses of category
            },
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        Box(
            modifier = Modifier
                .height(60.dp)
                .width(60.dp)
                .border(
                    width = 1.dp,
                    color = colorResource(id = R.color.hint_color),
                    shape = CircleShape
                )

        ) {
            Image(
                painter = rememberAsyncImagePainter(R.drawable.image_brand_logo),
                contentDescription = "image category",
                modifier = Modifier
                    .height(50.dp)
                    .width(50.dp)
                    .padding(4.dp)
                    .clip(CircleShape)
                    .align(Alignment.Center)
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = category.title,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp
        )
    }
}
