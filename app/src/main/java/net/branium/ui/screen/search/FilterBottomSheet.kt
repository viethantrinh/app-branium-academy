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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.branium.R
import net.branium.data.model.dto.response.home.Category

@Composable
fun FilterBottomSheet(
    categories: List<Category>,
    onApplyFilter: (String, String) -> Unit,
    onCancelFilter: () -> Unit
) {
    var selectedCategory by remember { mutableStateOf<String?>("") }
    var selectedSort by remember { mutableStateOf<String?>("") }

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(top = 12.dp),
    ) {
        Text("Sort By", fontWeight = FontWeight(500), fontSize = 16.sp)
        //them 3 check box ở đây SortAsc, SortDec, popular
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            SortOption(
                title = "Sort Asc",
                isSelected = selectedSort == "priceAsc",
                onClick = { selectedSort = if (selectedSort == "priceAsc") "" else "priceAsc" }
            )
            SortOption(
                title = "Sort Desc",
                isSelected = selectedSort == "priceDesc",
                onClick = { selectedSort = if (selectedSort == "priceDesc") "" else "priceDesc" }
            )
            SortOption(
                title = "Popular",
                isSelected = selectedSort == "popular",
                onClick = { selectedSort = if (selectedSort == "popular") "" else "popular" }
            )
        }
        Spacer(modifier = Modifier.height(16.dp))


        Text("Category", fontWeight = FontWeight(500), fontSize = 16.sp)
        LazyColumn(
            modifier = Modifier
                .padding(16.dp)
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(categories) { category ->
                FilterCategoryItem(
                    category = category,
                    isChecked = selectedCategory == category.title.lowercase(),
                    onChangeChecked = { isChecked ->
                        if (isChecked) {
                            selectedCategory = category.title.lowercase()
                        } else {
                            selectedCategory = ""
                        }
                    }
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        // Nút Apply và Cancel
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {
                    onApplyFilter(
                        selectedSort.toString(),
                        selectedCategory.toString()
                    )
                }, // Truyền sort và category đã chọn
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.primary_400),
                    contentColor = Color.White
                )
            ) {
                Text("Apply")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = {
                onCancelFilter()
                selectedSort = ""
                selectedCategory = ""
            },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.primary_400),
                    contentColor = Color.White
                )
            ) { // Xóa trạng thái
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
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = isChecked,
            onCheckedChange = onChangeChecked
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = category.title,
            fontSize = 14.sp,
            fontWeight = FontWeight(400)
        )
    }
}


@Composable
fun SortOption(
    title: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = isSelected,
            onCheckedChange = { onClick() } // Cập nhật trạng thái khi nhấp
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = title,
            fontSize = 14.sp,
            fontWeight = FontWeight(400)
        )
    }
}
