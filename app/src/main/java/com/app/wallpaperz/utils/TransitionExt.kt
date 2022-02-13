package com.app.wallpaperz.utils

import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.Transition
import androidx.compose.animation.core.animateValue
import androidx.compose.animation.core.spring
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.unit.TextUnit

@Composable
inline fun <S> Transition<S>.animateSp(
    noinline transitionSpec: @Composable Transition.Segment<S>.() -> FiniteAnimationSpec<TextUnit> = {
        spring(visibilityThreshold = TextUnit.VisibilityThreshold)
    },
    label: String = "SpAnimation",
    targetValueByState: @Composable (state: S) -> TextUnit
): State<TextUnit> =
    animateValue(TextUnit.VectorConverter, transitionSpec, label, targetValueByState)
