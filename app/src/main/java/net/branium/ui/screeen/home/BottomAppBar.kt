package net.branium.ui.screeen.home


import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

import net.branium.R

@Composable
fun CustomBottomAppBar(
    onHomeClick: () -> Unit,
    onSearchClick: () -> Unit,
    onResourcesClick: () -> Unit,
    onWishlistClick: () -> Unit,
    onUserClick: () -> Unit
) {

    BottomAppBar(
        containerColor = colorResource(id = R.color.hint_color),
        modifier = Modifier.height(40.dp)


    ) {

        IconButton(
            onClick = {
                onHomeClick()
            },
            modifier = Modifier.weight(1f)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_home),
                contentDescription = "Home"
            )
        }
        IconButton(
            onClick = { onSearchClick() }, modifier = Modifier.weight(1f)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = "Home"
            )
        }
        IconButton(
            onClick = { onResourcesClick() }, modifier = Modifier.weight(1f)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_video_libraly),
                contentDescription = "Home"
            )
        }
        IconButton(
            onClick = { onWishlistClick() }, modifier = Modifier.weight(1f)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_favorite_border),
                contentDescription = "Home"
            )
        }
        IconButton(
            onClick = { onUserClick() }, modifier = Modifier.weight(1f)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_account_circle),
                contentDescription = "Home"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CustomBottomAppBarPreview() {
    CustomBottomAppBar({}, {}, {}, {}, {})
}