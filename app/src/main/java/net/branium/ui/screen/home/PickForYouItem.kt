package net.branium.ui.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import net.branium.R
import net.branium.data.model.dto.response.home.TopPick

@Composable
fun PickForYouItem(topPick: TopPick) {
    Column(
        modifier = Modifier
            .height(160.dp)
            .width(172.dp),
    ) {
        Box(modifier = Modifier
            .height(72.dp)
            .width(170.dp)
            .border(
                width = 0.5.dp,
                color = colorResource(id = R.color.hint_color),
            )
            .background(colorResource(id =  R.color.hint_color))
        ){
            Image(
                modifier = Modifier.fillMaxSize(), // Để Image chiếm toàn bộ Box
                painter = rememberAsyncImagePainter(topPick.image),
                contentDescription = "rectangle image",
                contentScale = ContentScale.FillWidth
            )
        }
        Spacer(modifier = Modifier.height(8.dp))

        Box(
            modifier = Modifier
                .height(80.dp)
                .width(170.dp)
        ) {
            Column {
                Text(
                    text = topPick.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
                Text(
                    text = "Trinh Viet Han",
                    fontSize = 8.sp
                )
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
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
                            painter = painterResource(id = R.drawable.star_rate_24),
                            contentDescription = "star rate",
                            colorFilter = ColorFilter.tint(colorResource(id = R.color.yellow))
                        )
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.padding(end = 4.dp),
                        text = "${topPick.price}đ",
                        fontSize = 10.sp,
                        textDecoration = TextDecoration.LineThrough
                        )
                    Text(
                        modifier = Modifier.padding(start = 4.dp),
                        text = "${topPick.discountPrice}đ",
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
fun PickForYouItemPreview() {
    PickForYouItem(topPick = TopPick(1, "Java core", "", 11.0, 10.0))
}