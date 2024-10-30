package net.branium.ui.screen.course

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import net.branium.R
import net.branium.data.model.dto.response.course.CourseDetailResponse
import net.branium.data.model.dto.response.course.Lecture
import net.branium.data.model.dto.response.course.Section
import net.branium.util.getOptionCourseDetail
import net.branium.util.splitDescription
import net.branium.viewmodel.ApiResponseState
import net.branium.viewmodel.CourseViewModel

@Composable
fun CourseDetailScreen(courseId: Int, onNavigateToCourseVideoScreen: (Int) -> Unit) {
    val context = LocalContext.current
    val courseViewModel: CourseViewModel = hiltViewModel()
    LaunchedEffect(courseId) {
        courseViewModel.getCourseDetail(courseId)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        when(val responseState = courseViewModel.apiResponseState.value){
            is ApiResponseState.Succeeded -> {
                val courseDetail = courseViewModel.courseDetail.value
                CourseIntroduce(courseDetail = courseDetail)
                OptionScreen(courseDetail = courseDetail){courseId ->
                    onNavigateToCourseVideoScreen(courseId)
                }
                Spacer(modifier = Modifier.height(32.dp))
                WillLearnScreen(courseDetail = courseDetail)
                Spacer(modifier = Modifier.height(32.dp))
                if(courseDetail.sections.isNotEmpty()){
                    CurriculumScreen(courseDetail = courseDetail)
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

}

@Composable
fun CourseIntroduce(courseDetail: CourseDetailResponse) {
    Image(
        painter = rememberAsyncImagePainter(courseDetail.image),
        contentDescription = null,
        modifier = Modifier
            .width(382.dp)
            .height(160.dp),
        contentScale = ContentScale.Crop
    )
    Spacer(modifier = Modifier.height(8.dp))
    Text(
        text = courseDetail.title,
        fontSize = 20.sp,
        fontWeight = FontWeight(600)
    )
    Spacer(modifier = Modifier.height(8.dp))
    Text(
        text = courseDetail.shortDescription,
        fontSize = 12.sp,
        fontWeight = FontWeight(400),
        color = colorResource(id = R.color.sencondary)
    )
    Spacer(modifier = Modifier.height(4.dp))
    Box(
        modifier = Modifier
            .width(55.dp)
            .height(24.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(colorResource(id = R.color.primary_400))

    ) {
        Text(
            text = "Popular",
            fontSize = 10.sp,
            modifier = Modifier.align(Alignment.Center),
            color = Color.White
        )
    }
}

@Composable
fun OptionScreen(courseDetail: CourseDetailResponse, onNavigateToCourseVideoScreen: (Int) -> Unit) {
    val option = getOptionCourseDetail(courseDetail.paid, courseDetail.enrolled)
    when (option) {
        OptionCourseDetailScreen.LearnNowOption.option -> {
            LearnNowOptionCourseScreen(courseDetail = courseDetail, onNavigateToCourseVideoScreen)
        }
        OptionCourseDetailScreen.EnrollOption.option -> {
            EnrollOptionCourseScreen(courseDetail = courseDetail)
        }
        OptionCourseDetailScreen.BuyNowOption.option ->{
            BuyNowOptionCourseScreen(courseDetail = courseDetail)
        }
        else -> {
            Unit
        }
    }
}

@Composable
fun WillLearnScreen(courseDetail: CourseDetailResponse) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Column {
            Text(
                text = "What you will learn",
                fontSize = 14.sp,
                fontWeight = FontWeight(500),
                color = colorResource(id = R.color.primary)
            )
            Spacer(modifier = Modifier.height(8.dp))
            val descriptions = splitDescription(courseDetail.fullDescription)
            descriptions.forEach { description ->
                Row {
                    Icon(
                        painter = painterResource(id = R.drawable.icon_draw_24),
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = description, fontSize = 12.sp, fontWeight = FontWeight(400))
                    Spacer(modifier = Modifier.height(4.dp))

                }
            }
        }
    }

}

@Composable
fun CurriculumScreen(courseDetail: CourseDetailResponse) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Curriculum",
            fontSize = 14.sp,
            fontWeight = FontWeight(500),
            color = colorResource(id = R.color.primary)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "${courseDetail.totalSections} sections, ${courseDetail.totalLectures} lectures," +
                    " duration: ${courseDetail.totalDuration}",
            fontSize = 10.sp,
            fontWeight = FontWeight(400)

        )
        Spacer(modifier = Modifier.height(16.dp))
        // Danh sách trạng thái cho mỗi section
        val expandedSections =
            remember { mutableStateListOf<Boolean>().apply { addAll(List(courseDetail.sections.size) { false }) } }

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = 400.dp)
                .wrapContentHeight(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            val sections = courseDetail.sections
            items(sections.indices.toList()) { index ->
                SectionHeader(sections[index], expandedSections[index]) {
                    // Chuyển đổi trạng thái mở/đóng
                    expandedSections[index] = !expandedSections[index]
                }
                if (expandedSections[index]) {
                    sections[index].lectures.forEach { lecture ->
                        LectureItem(lecture)
                        Divider(
                            color = Color.Gray,
                            thickness = 0.5.dp,
                            modifier = Modifier
                                .padding(vertical = 8.dp)
                                .padding(start = 16.dp, end = 16.dp)
                        )
                    }
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun CourseDetailPreview() {

}

@Composable
fun SectionHeader(section: Section, isExpanded: Boolean, onSectionClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable { onSectionClick() },
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Section ${section.order}: ${section.title}",
            fontSize = 14.sp,
            fontWeight = FontWeight(600),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis

        )
        Spacer(modifier = Modifier.width(4.dp))
        Icon(
            modifier = Modifier.size(20.dp),
            imageVector = (if (isExpanded) {
                ImageVector.vectorResource(id = R.drawable.icon_arrow_down_24)
            } else {
                ImageVector.vectorResource(id = R.drawable.icon_arrow_up_24)
            }),
            contentDescription = null
        )


    }
}

@Composable
fun LectureItem(lecture: Lecture) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
    )
    {
        Row(
            modifier = Modifier
                .width(343.dp)
                .wrapContentHeight()
                .padding(start = 16.dp, end = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "${lecture.order}",
                fontSize = 12.sp,
                fontWeight = FontWeight(400)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(36.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = lecture.title,
                    fontSize = 12.sp,
                    fontWeight = FontWeight(400)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Video - ${lecture.duration}",
                    fontSize = 10.sp,
                    fontWeight = FontWeight(400)
                )
            }
        }
    }
}

// Dữ liệu giả của CourseDetailResponse
val fakeCourseDetail = CourseDetailResponse(
    id = 2,
    title = "Khóa học lập trình Java cơ bản",
    image = "https://be-branium-academy-ad1581e47cac.herokuapp.com/branium-academy/api/v1/resources/images/IypolLPp",
    shortDescription = "Khóa học này cung cấp cho các bạn kiến thức từ cơ bản của ngôn ngữ lập trình Java.",
    fullDescription = "Khóa học được lồng ghép các yêu cầu nâng cao vào các bài học để bạn có thể đáp ứng được yêu cầu của nhà tuyển dụng ngày càng tăng#Sau khi kết thúc khóa học bạn có thể tự tin đi thực tập, intern, fresher vị trí Mobile App Developer với Flutter#Khóa học sẽ được cập nhật, thay đổi nội dung mới nhất mà không có thông báo trước",
    price = 399000.00,
    discountPrice = 349000.00,
    updatedAt = "2024-10-23",
    totalStudents = 200,
    totalSections = 5,
    totalLectures = 36,
    totalDuration = "03 hours 23 minutes",
    paid = true,
    enrolled = true,
    inCart = false,
    inWishList = false,
    sections = listOf(
        Section(
            id = 11,
            title = "Chương 1: Chuẩn bị trước khi học",
            order = 1,
            lectures = listOf(
                Lecture(
                    id = 50,
                    title = "Bài 1.1. Những điều cần chuẩn bị trước khi học",
                    order = 1,
                    type = "video",
                    resource = "https://be-branium-academy-ad1581e47cac.herokuapp.com/branium-academy/api/v1/resources/videos/pzIeMntY",
                    duration = "06:22"
                ),
                Lecture(
                    id = 51,
                    title = "Bài 1.2. Giới thiệu về ngôn ngữ Java",
                    order = 2,
                    type = "video",
                    resource = "https://be-branium-academy-ad1581e47cac.herokuapp.com/branium-academy/api/v1/resources/videos/abc123",
                    duration = "08:45"
                )
            )
        ),
        Section(
            id = 12,
            title = "Chương 2: Cấu trúc ngôn ngữ Java",
            order = 2,
            lectures = listOf(
                Lecture(
                    id = 52,
                    title = "Bài 2.1. Cách viết chương trình đầu tiên",
                    order = 1,
                    type = "video",
                    resource = "https://be-branium-academy-ad1581e47cac.herokuapp.com/branium-academy/api/v1/resources/videos/def456",
                    duration = "10:30"
                ),
                Lecture(
                    id = 53,
                    title = "Bài 2.2. Các thành phần cơ bản của Java",
                    order = 2,
                    type = "video",
                    resource = "https://be-branium-academy-ad1581e47cac.herokuapp.com/branium-academy/api/v1/resources/videos/ghi789",
                    duration = "12:15"
                )
            )
        )
    )
)
