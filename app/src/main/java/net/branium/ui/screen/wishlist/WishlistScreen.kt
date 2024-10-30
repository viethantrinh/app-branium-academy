package net.branium.ui.screen.wishlist

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import net.branium.data.model.dto.response.course.CourseResponse
import net.branium.data.model.dto.response.home.TopPick
import net.branium.viewmodel.ApiResponseState
import net.branium.viewmodel.ResponseState
import net.branium.viewmodel.WishlistViewModel

@Composable
fun WishlistScreen(onNavigateToDetailCourse: (Int) -> Unit) {
    val context = LocalContext.current
    val wishlistViewModel : WishlistViewModel = hiltViewModel()
    val wishlist = wishlistViewModel.wishListItems.value
    LaunchedEffect(wishlistViewModel) {
         wishlistViewModel.getAllWishListItems()
    }
    Spacer(modifier = Modifier.height(16.dp))
    LazyColumn(
        modifier = Modifier.padding(top = 32.dp, start = 8.dp, end = 8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        when(val apiResponseState = wishlistViewModel.apiResponseState.value){
            is ResponseState.Succeeded ->{

                items(wishlist){
                        course ->
                    WishlistItemScreen(course = course){courseId ->
                        onNavigateToDetailCourse(courseId)
                    }

                }
            }
            is ResponseState.Failed -> {
                Toast.makeText(context, apiResponseState.message, Toast.LENGTH_SHORT).show()
            }

            is ResponseState.Processing ->{
                Toast.makeText(context, apiResponseState.message, Toast.LENGTH_SHORT).show()
            }
            else -> Unit
        }

    }

}

@Preview(showBackground = true)
@Composable
fun WishlistScreenPreview() {
    WishlistScreen({})
}
