package net.branium.ui.screen.account

import android.app.DatePickerDialog
import android.widget.DatePicker
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import net.branium.R
import net.branium.data.model.dto.request.user.UserInfoRequest
import net.branium.viewmodel.UserViewModel
import java.util.Calendar

@Composable
fun UpdateUserInfoScreen(
    userViewModel: UserViewModel,
    onUpdateSuccess: () -> Unit,
    navController: NavController
) {

    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var dayOfBirth by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }

    // Calendar Instance for DatePicker
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)
    val context = LocalContext.current

    // DatePickerDialog
    val datePickerDialog = DatePickerDialog(
        LocalContext.current, // context
        { _: DatePicker, selectedYear: Int, selectedMonth: Int, selectedDay: Int ->
            dayOfBirth = "$selectedYear-${selectedMonth + 1}-$selectedDay" // yyyy-MM-dd
        },
        year, month, day
    )


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 24.dp)
            .verticalScroll(rememberScrollState())
    ) {
        // Tiêu đề
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center,
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Information",
                    fontWeight = FontWeight(600),
                    fontSize = 24.sp,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "Update your information",
                    fontWeight = FontWeight(400),
                    fontSize = 16.sp,
                    modifier = Modifier.padding(bottom = 24.dp)
                )
            }
        }


        // Input Fields
        Text(text = "First Name")
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = firstName,
            onValueChange = { firstName = it },
            placeholder = {
                Text(
                    text = "First Name",
                    color = colorResource(id = R.color.color_hint_input_text_field)
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = colorResource(id = R.color.primary_400)
            ),
            maxLines = 1
        )

        Text(text = "Last Name")
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = lastName,
            onValueChange = { lastName = it },
            placeholder = {
                Text(
                    text = "Last Name",
                    color = colorResource(id = R.color.color_hint_input_text_field)
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = colorResource(id = R.color.primary_400),
            ),
            maxLines = 1
        )

        Text(text = "Day of Birth")
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = dayOfBirth,
            onValueChange = { },
            placeholder = {
                Text(
                    text = "YYYY-MM-dd",
                    color = colorResource(id = R.color.color_hint_input_text_field)
                )
            },
            trailingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_calendar_month_24),
                    contentDescription = null,
                    tint = colorResource(id = R.color.color_hint_input_text_field),
                    modifier = Modifier.clickable {
                        datePickerDialog.show()
                    }
                )
            },
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = colorResource(id = R.color.primary_400)
            ),
            maxLines = 1
        )

        Text(text = "Phone Number")
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            placeholder = {
                Text(
                    text = "Phone number",
                    color = colorResource(id = R.color.color_hint_input_text_field)
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = colorResource(id = R.color.primary_400)
            ),
            maxLines = 1
        )

        // Buttons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    //validation
                    if (firstName.isEmpty() || lastName.isEmpty() || dayOfBirth.isEmpty() || phoneNumber.isEmpty()) {
                        Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
                        return@Button
                    }
                    val userRequest =
                        UserInfoRequest(firstName, lastName, false, dayOfBirth, phoneNumber)

                    userViewModel.updateUserInfo(userRequest)
                    userViewModel.getUserInfo()
                    onUpdateSuccess()
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF3D839D), // Primary Button
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(4.dp)
            ) {
                Text("Save")
            }

            OutlinedButton(
                onClick = {
                    navController.popBackStack()
                },
                modifier = Modifier.weight(1f),
                border = BorderStroke(1.dp, Color.Gray),
                shape = RoundedCornerShape(4.dp)
            ) {
                Text("Cancel", color = Color.Black)
            }
        }
    }
}
