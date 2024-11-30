package net.branium.ui.screen.search

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.branium.R
import net.branium.viewmodel.SearchViewModel

@Composable
fun SearchBarCourse(
    searchViewModel: SearchViewModel,
) {
    val searchText by searchViewModel.searchText.collectAsState()
    val hasFocus by searchViewModel.isFocused.collectAsState()

    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current


    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (hasFocus){
            IconButton(
                onClick = {
                    searchViewModel.onSearchTextChange("") // Xóa text
                    focusManager.clearFocus()
                },
                modifier = Modifier.wrapContentSize()
            ) {
                Icon(imageVector = Icons.Default.KeyboardArrowLeft, contentDescription = "Back")
            }
        }
        OutlinedTextField(
            value = searchText,
            onValueChange = {
                searchViewModel.onSearchTextChange(it)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
                .focusRequester(focusRequester)
                .onFocusChanged { focusState ->
                    searchViewModel.updateFocusState(focusState.hasFocus)
                    Log.d("HHHH", "SearchBarCourse: ${focusState.hasFocus}")
                },
            placeholder = { Text("Search", fontSize = 10.sp, fontWeight = FontWeight(400)) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    Modifier.size(12.dp)
                )
            },
            trailingIcon = {
                if (searchText.isNotBlank()) {
                    IconButton(onClick = { searchViewModel.showBottomSheet() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_filter_list_24),
                            contentDescription = "Filter",
                            Modifier.size(15.dp, 10.dp)
                        )
                    }
                }
            },
            singleLine = true,
            shape = RoundedCornerShape(50.dp),// bo góc tròn giống Udemy

        )
    }
}


@Preview(showBackground = true)
@Composable
fun SearchBarPreview() {

}