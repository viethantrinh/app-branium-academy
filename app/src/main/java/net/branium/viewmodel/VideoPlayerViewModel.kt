package net.branium.viewmodel

import android.app.Activity
import android.content.Context
import android.content.pm.ActivityInfo
import android.content.pm.ActivityInfo.SCREEN_ORIENTATION_USER
import android.content.pm.ActivityInfo.SCREEN_ORIENTATION_USER_LANDSCAPE
import android.net.Uri
import androidx.annotation.OptIn
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import dagger.hilt.android.lifecycle.HiltViewModel
import net.branium.data.model.dto.response.course.Section
import javax.inject.Inject

@OptIn(UnstableApi::class)
class VideoPlayerViewModel : ViewModel() {
    private var exoPlayer: ExoPlayer? = null

    fun initializePlayer(context: Context) {
        exoPlayer = ExoPlayer.Builder(context).build()
    }

    fun releasePlayer() {
        exoPlayer?.playWhenReady = false
        exoPlayer?.release()
        exoPlayer = null
    }

    fun playVideo(url: String) {
        exoPlayer?.let { player ->
            player.apply {
                stop()
                clearMediaItems()
                setMediaItem(MediaItem.fromUri(url))
                playWhenReady = true
                prepare()
                play()
            }
        }
    }

    fun playerViewBuilder(context: Context): PlayerView {
        val activity = context as Activity
        val playerView = PlayerView(context).apply {
            player = exoPlayer
            controllerAutoShow = true
            keepScreenOn = true
            setFullscreenButtonClickListener { isFullScreen ->
                if (isFullScreen) {
                    activity.requestedOrientation = SCREEN_ORIENTATION_USER_LANDSCAPE
                } else {
                    activity.requestedOrientation = SCREEN_ORIENTATION_USER
                }
            }
        }
        return playerView
    }

}