package net.branium.ui.screen.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.branium.data.model.dto.response.home.Category

@Composable
fun FilterBottomSheet(
    categories: List<Category>,
    onApplyFilter: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(top = 50.dp),
    ) {
        Text("Sort By", fontWeight = FontWeight(500), fontSize = 14.sp)
        Spacer(modifier = Modifier.height(16.dp))

        Text("Category", fontWeight = FontWeight(500), fontSize = 14.sp)
        LazyColumn(
            modifier = Modifier
                .padding(16.dp)
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ){
            items(categories){
                category ->
                FilterCategoryItem(category = category, isChecked = false) {

                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
            ,horizontalArrangement = Arrangement.Center
        ) {
            Button(onClick = onApplyFilter) {
                Text("Apply")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = {  }) {
                Text("Cancel")
            }
        }
    }
}


@Composable
fun FilterCategoryItem(
    category: Category,
    isChecked: Boolean,
    onChangeChecked: (Boolean) -> Unit
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Checkbox(checked = isChecked, onCheckedChange = onChangeChecked)
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = category.title, fontSize = 10.sp, fontWeight = FontWeight(400))
    }

}