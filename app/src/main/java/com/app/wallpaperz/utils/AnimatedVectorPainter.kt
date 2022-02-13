package com.app.wallpaperz.utils

import androidx.annotation.DrawableRes
import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.animation.graphics.res.animatedVectorResource
import androidx.compose.animation.graphics.res.rememberAnimatedVectorPainter
import androidx.compose.animation.graphics.vector.AnimatedImageVector
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.painter.Painter
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalAnimationGraphicsApi::class)
@Composable
fun rememberInfiniteAnimatedVectorPainter(@DrawableRes id: Int, duration: Long): Painter {
    val image = AnimatedImageVector.animatedVectorResource(id)
    var atEnd by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(image) {
        launch {
            while (true) {
                atEnd = !atEnd
                delay(duration)
            }
        }
    }
    return rememberAnimatedVectorPainter(animatedImageVector = image, atEnd = atEnd)
}
