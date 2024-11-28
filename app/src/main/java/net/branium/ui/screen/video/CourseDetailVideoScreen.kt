@file:Suppress("NAME_SHADOWING")

package net.branium.ui.screen.video

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import net.branium.data.model.dto.response.course.Lecture
import net.branium.data.model.dto.response.course.Section
import net.branium.viewmodel.CourseViewModel
import net.branium.viewmodel.VideoPlayerViewModel


@Composable
fun CourseDetailVideoScreen(
    courseId: Int,
    navController: NavController,
    onNavigationToExam: (Int) -> Unit
) {
    val courseDetailViewModel: CourseViewModel = hiltViewModel()
    val viewModel: VideoPlayerViewModel = viewModel()
    LaunchedEffect(courseId) {
        courseDetailViewModel.getCourseDetail(courseId)
    }
    val courseDetail = courseDetailViewModel.courseDetail.value
    if (courseDetail.sections.isNotEmpty()) {
        val sections = courseDetailViewModel.courseDetail.value.sections
        var isPlaying by remember {
            mutableStateOf(false)
        }
        var url by remember {
            mutableStateOf(
                sections.firstOrNull()?.lectures?.firstOrNull()?.resource ?: ""
            )
        }

        var stateLectureId by remember {
            mutableStateOf(-1)
        }


        Log.d("TAG", "CourseDetailVideoScreen: $url")
        val context = LocalContext.current

        Column {
            StreamerPlayer(
                viewModel = viewModel,
                isPlaying = isPlaying,
                onPlayerClosed = { isVideoPlaying ->
                    isPlaying = isVideoPlaying
                },
                navController = navController

            )

            Column(
                modifier = Modifier.padding(
                    start = 16.dp,
                    end = 16.dp,
                    top = 16.dp,
                    bottom = 4.dp
                )
            ) {
                Text(
                    text = courseDetail.title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight(600)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Trinh Han",
                    fontSize = 12.sp,
                    fontWeight = FontWeight(400)
                )
            }
            Divider(
                color = Color.Gray,
                thickness = 0.5.dp,
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .wrapContentWidth()
            )

            // Danh sách trạng thái cho mỗi section
            val expandedSections =
                remember { mutableStateListOf<Boolean>().apply { addAll(List(courseDetail.sections.size) { false }) } }
            // Danh sách section và lecture
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                val sections = courseDetail.sections
                items(sections.indices.toList()) { index ->
                    SectionHeader(
                        sections[index],
                        expandedSections[index]
                    ) {
                        // Chuyển đổi trạng thái mở/đóng
                        expandedSections[index] = !expandedSections[index]
                    }

                    if (expandedSections[index]) {
                        sections[index].lectures.forEach { lecture ->
                            LectureVideoItem(lecture = lecture,
                                onClickPlayVideo = { lectureId, lectureUrl ->
//                                    if (it != url) {
//                                        isPlaying = false
//                                        url = it
//                                        Log.d("TAG", "CourseDetailVideoScreen: $url")
//                                    } else {
//                                        Log.d("TAG", "Ko thay doi")
//                                    }
                                    stateLectureId = lectureId
                                    url = lectureUrl
                                    isPlaying = false
                                },
                                onNavigationToExam = { lectureId ->
                                    isPlaying = false
                                    onNavigationToExam(lectureId)
                                })
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

        LaunchedEffect(key1 = stateLectureId) {
            isPlaying = true
            viewModel.apply {
                releasePlayer()
                initializePlayer(context)
                playVideo(url)
            }
        }
    }
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
            text = "${section.title}",
            fontSize = 14.sp,
            fontWeight = FontWeight(600),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis

        )
        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
fun LectureVideoItem(
    lecture: Lecture,
    onClickPlayVideo: (Int, String) -> Unit,
    onNavigationToExam: (Int) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable {
                if (lecture.type == "video") {
                    onClickPlayVideo(lecture.id, lecture.resource)
                } else if (lecture.type == "quiz") {
                    onNavigationToExam(lecture.id) // sau thay lecture resource
                }
            },
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
                    fontWeight = FontWeight(400),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
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


