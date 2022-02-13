package com.app.wallpaperz.ui.components

import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.wallpaperz.utils.animateSp

@Composable
fun CollapsingTopAppBar(
    modifier: Modifier = Modifier,
    title: String,
    scrolledToTop: Boolean
) {
    val topAppBarState = if (scrolledToTop) {
        TopAppBarState.Expanded
    } else {
        TopAppBarState.Collapsed
    }
    val transitionData = updateTopAppBarTransitionData(topAppBarState)
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(transitionData.topAppBarHeight)
    ) {
        Text(
            modifier = Modifier
                .padding(transitionData.titlePaddingStart)
                .align(
                    BiasAlignment(
                        horizontalBias = transitionData.horizontalBias,
                        verticalBias = transitionData.verticalBias
                    )
                ),
            text = title,
            fontSize = transitionData.titleFontSize
        )
    }
}

@Composable
fun updateTopAppBarTransitionData(topAppBarState: TopAppBarState): TopAppBarTransitionData {
    val transition = updateTransition(targetState = topAppBarState, label = "TopAppBArTransition")
    val titlePaddingStart = transition.animateDp(label = "titlePaddingStartDpAnim") { state ->
        when (state) {
            TopAppBarState.Expanded -> 8.dp
            TopAppBarState.Collapsed -> 0.dp
        }
    }
    val topAppBarHeight = transition.animateDp(label = "topAppBarHeightDpAnim") { state ->
        when (state) {
            TopAppBarState.Expanded -> 152.dp
            TopAppBarState.Collapsed -> 56.dp
        }
    }
    val titleFontSize = transition.animateSp(label = "titleFontSizeSpAnim") { state ->
        when (state) {
            TopAppBarState.Expanded -> 32.sp
            TopAppBarState.Collapsed -> 18.sp
        }
    }
    val horizontalBias = transition.animateFloat(label = "horizontalBiasFloatAnim") { state ->
        when (state) {
            TopAppBarState.Expanded -> -1f
            TopAppBarState.Collapsed -> 0f
        }
    }
    val verticalBias = transition.animateFloat(label = "verticalBiasFloatAnim") { state ->
        when (state) {
            TopAppBarState.Expanded -> 1f
            TopAppBarState.Collapsed -> 0f
        }
    }
    return remember(transition) {
        TopAppBarTransitionData(
            titlePaddingStart = titlePaddingStart,
            titleFontSize = titleFontSize,
            horizontalBias = horizontalBias,
            verticalBias = verticalBias,
            topAppBarHeight = topAppBarHeight
        )
    }
}

class TopAppBarTransitionData(
    titlePaddingStart: State<Dp>,
    titleFontSize: State<TextUnit>,
    horizontalBias: State<Float>,
    verticalBias: State<Float>,
    topAppBarHeight: State<Dp>
) {
    val titlePaddingStart by titlePaddingStart
    val titleFontSize by titleFontSize
    val horizontalBias by horizontalBias
    val verticalBias by verticalBias
    val topAppBarHeight by topAppBarHeight
}

enum class TopAppBarState {
    Expanded,
    Collapsed
}
