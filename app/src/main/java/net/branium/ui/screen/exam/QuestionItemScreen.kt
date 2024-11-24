package net.branium.ui.screen.exam

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.branium.data.model.dto.request.exam.Answer
import net.branium.data.model.dto.response.exam.QuestionResponse

@Composable
fun QuestionItemScreen(
//    navController: NavController,
    question: QuestionResponse,
    answer: Answer,
    onClickNext: (Int) -> Unit,
    navigationToViewResult: () -> Unit,
    isLastQuestion: Boolean
) {
    var selectedAnswer by remember { mutableIntStateOf(-1) } // Lưu số thứ tự của đáp án đã chọn
    var selectedOption by remember { mutableStateOf<OptionButton?>(OptionButton.ShowAnswer) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 42.dp, start = 16.dp, end = 16.dp, bottom = 32.dp)
    ) {
//        IconButton(onClick = { navController.navigateUp() }) {
//            Icon(
//                painter = painterResource(id = R.drawable.icon_arrow_back_ios_24),
//                contentDescription = "Back Button"
//            )
//        }
//        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Question ${question.id}: ${question.title}",
            fontSize = 16.sp,
            fontWeight = FontWeight(600)
        )
        Spacer(modifier = Modifier.height(16.dp))

        AnswerOption(
            answerText = question.answer1,
            isSelected = selectedAnswer == 1,
            onSelected = { selectedAnswer = 1 },
            isCorrect = question.isCorrect1,
            isShowAnswer = selectedOption == OptionButton.NextQuestion || selectedOption == OptionButton.ViewResult,
            enabledRadioButton = selectedOption == OptionButton.ShowAnswer
        )

        AnswerOption(
            answerText = question.answer2,
            isSelected = selectedAnswer == 2,
            onSelected = { selectedAnswer = 2 },
            isCorrect = question.isCorrect2,
            isShowAnswer = selectedOption == OptionButton.NextQuestion || selectedOption == OptionButton.ViewResult,
            enabledRadioButton = selectedOption == OptionButton.ShowAnswer
        )

        AnswerOption(
            answerText = question.answer3,
            isSelected = selectedAnswer == 3,
            onSelected = { selectedAnswer = 3 },
            isCorrect = question.isCorrect3,
            isShowAnswer = selectedOption == OptionButton.NextQuestion || selectedOption == OptionButton.ViewResult,
            enabledRadioButton = selectedOption == OptionButton.ShowAnswer
        )

        Box(
            modifier = Modifier.fillMaxHeight(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Button(
                onClick = {
                    if (selectedOption == OptionButton.ShowAnswer) {
                        //nếu chưa chon dap an thi thoat khoi ham
                        when (selectedAnswer) {
                            -1 -> {
                                return@Button
                            }
                        }
                        // Lưu đáp án và xác định đúng/sai
                        answer.questionId = question.id
                        answer.selectedOption = selectedAnswer
                        answer.isCorrect = when (selectedAnswer) {
                            1 -> question.isCorrect1
                            2 -> question.isCorrect2
                            3 -> question.isCorrect3
                            else -> false
                        }
                        if(isLastQuestion){
                            selectedOption = OptionButton.ViewResult
                        }else{
                            //chuyển trang thái button sang next question
                            selectedOption = OptionButton.NextQuestion
                        }
                    } else if (selectedOption == OptionButton.NextQuestion) {
                        onClickNext(question.id)
                        selectedOption = OptionButton.ShowAnswer
                        selectedAnswer = -1
                    }else if (selectedOption == OptionButton.ViewResult){
                        navigationToViewResult()
                    }
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
                    text = selectedOption?.text ?: "Null",
                    fontSize = 14.sp,
                    fontWeight = FontWeight(600),
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun AnswerOption(
    answerText: String,
    isSelected: Boolean,
    onSelected: () -> Unit,
    isCorrect: Boolean,
    isShowAnswer: Boolean,
    enabledRadioButton: Boolean
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            modifier = Modifier.size(20.dp),
            selected = isSelected,
            onClick = onSelected,
            enabled = enabledRadioButton
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = answerText, fontSize = 14.sp, fontWeight = FontWeight(600))
    }
    if (isSelected && isShowAnswer) {
        Text(
            text = if (isCorrect) "Correct" else "Wrong",
            modifier = Modifier.padding(top = 8.dp, start = 28.dp),
            color = if (isCorrect) Color.Green else Color.Red,
            fontSize = 14.sp,
            fontWeight = FontWeight(500)
        )
    }
    Spacer(modifier = Modifier.height(8.dp))
}

sealed class OptionButton(val text: String) {
    object ShowAnswer : OptionButton("Show Answer")
    object NextQuestion : OptionButton("Next")
    object ViewResult: OptionButton("View Result")

}

@Preview(showBackground = true)
@Composable
fun QuestionItemScreenPreview() {
    QuestionItemScreen( fakeQuestion, fakeAnswer, {}, {}, false)
}

val fakeQuestion = QuestionResponse(
    id = 1,
    title = "What is the capital of France? Choose the answer that is correct.",
    answer1 = "Paris",
    isCorrect1 = true,
    answer2 = "London",
    isCorrect2 = false,
    answer3 = "Berlin",
    isCorrect3 = false
)

val fakeAnswer = Answer(1, 0, false)


