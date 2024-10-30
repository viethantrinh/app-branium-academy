package net.branium.ui.screen.video

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import net.branium.R
import net.branium.viewmodel.VideoPlayerViewModel

@Composable
fun StreamerPlayer(
    viewModel: VideoPlayerViewModel,
    isPlaying: Boolean,
    onPlayerClosed: (isVideoPlaying: Boolean) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        contentAlignment = Alignment.Center
    ) {
        if (isPlaying) {
            AndroidView(
                modifier = Modifier.fillMaxWidth(),
                factory = { cont ->
                    viewModel.playerViewBuilder(cont)
                })
            IconButton(
                onClick = {
                    onPlayerClosed(false)
                    viewModel.releasePlayer()
                }, modifier = Modifier.align(
                    Alignment.TopEnd
                )
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "", tint = Color.White
                )
            }
        } else {
            Image(
                painter = painterResource(id = R.drawable.loading), contentDescription = ""
            )
        }
    }
}