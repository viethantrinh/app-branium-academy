package net.branium.ui.screen.exam

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.branium.viewmodel.ExamViewmodel

@Composable
fun ExamResultScreen(examViewmodel: ExamViewmodel, onNavigateToCourse: () -> Unit) {
    val examResult by examViewmodel.examResult.collectAsState()

    Column(
        modifier = Modifier.padding(top = 82.dp, start = 16.dp, end = 16.dp),
    ) {
        Text(
            text = "Result",
            fontSize = 24.sp,
            fontWeight = FontWeight(600)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "${examResult.result} questions", fontSize = 16.sp,
            fontWeight = FontWeight(600),
            color = Color(0xFFF95E0A)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "You have not achieved the minimum score, " +
                    "please review the section to better understand the knowledge."
        )
        Box(
            modifier = Modifier.fillMaxHeight(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Button(
                onClick = {
                    onNavigateToCourse()
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
                    text = "Back to Course",
                    fontSize = 14.sp,
                    fontWeight = FontWeight(600),
                    color = Color.White
                )
            }
        }
    }


}