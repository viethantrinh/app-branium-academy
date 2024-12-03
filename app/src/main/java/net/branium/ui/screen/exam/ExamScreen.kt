@file:Suppress("KotlinConstantConditions")

package net.branium.ui.screen.exam

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.branium.data.model.dto.request.exam.Answer
import net.branium.data.model.dto.request.exam.ExamRequest
import net.branium.viewmodel.ApiResponseState
import net.branium.viewmodel.ExamViewmodel

@Composable
fun ExamScreen(examId: Int, examViewmodel: ExamViewmodel, navigationToViewResult: () -> Unit) {
    var isStarted by remember { mutableStateOf(false) }
    var questionCurrentIndex by remember { mutableIntStateOf(0) }
    val context = LocalContext.current

    LaunchedEffect(examId) {
        examViewmodel.getExamData(examId)
    }
    val examData by examViewmodel.examData.collectAsState()
    val answerRequest = rememberSaveable(examData.questionResponses.size) {
        MutableList(examData.questionResponses.size) { Answer() }
    }


    if (!isStarted) {
        //hien thi man hinh Quiz test
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 24.dp, start = 16.dp, end = 16.dp, bottom = 32.dp)
        ) {
            Text(
                text = examData.title,
                fontSize = 16.sp,
                fontWeight = FontWeight(600)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Câu hỏi câu hỏi: " + examData.questionCount.toString(),
                fontSize = 12.sp,
                fontWeight = FontWeight(400)
            )
            Spacer(modifier = Modifier.height(32.dp))
            Text(text = examData.description, fontSize = 12.sp, fontWeight = FontWeight(300))
            Box(
                modifier = Modifier.fillMaxHeight(),
                contentAlignment = Alignment.BottomCenter
            ) {
                Button(
                    onClick = {
                        isStarted = true
                    },
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black,
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(size = 8.dp)
                ) {
                    Text(
                        text = "Start",
                        fontSize = 14.sp,
                        fontWeight = FontWeight(600),
                        color = Color.White
                    )
                }
            }
        }
    } else {
        //hien thi cau hoi neu van con cau hoi
        if (questionCurrentIndex < examData.questionResponses.size) {
            val question = examData.questionResponses[questionCurrentIndex]
            val answer = answerRequest[questionCurrentIndex]
            QuestionItemScreen(
                question = question,
                answer = answer,
                onClickNext = {
                    questionCurrentIndex = it
                },
                navigationToViewResult = {
                    // Convert answerRequest list to ExamRequest and navigate to view results
                    val examRequest = ExamRequest(
                        quizId = examId,
                        answers = answerRequest
                    )
                    Log.d("ExamRequest", "Request data: ${examRequest.toString()}")

                    examViewmodel.submitExamResult(examRequest)// Example function to handle submission
                    when(val responseState = examViewmodel.apiResponseState.value){is ApiResponseState.Succeeded -> {
                            navigationToViewResult()
                        }

                        is ApiResponseState.Failed -> {
                            Toast.makeText(context, responseState.message, Toast.LENGTH_SHORT).show()
                        }

                        is ApiResponseState.Processing -> {
                            Toast.makeText(context, responseState.message, Toast.LENGTH_SHORT).show()
                        }

                        else -> Unit
                    }

                },
                isLastQuestion = questionCurrentIndex == examData.questionResponses.size - 1
            )
        }


    }

}

