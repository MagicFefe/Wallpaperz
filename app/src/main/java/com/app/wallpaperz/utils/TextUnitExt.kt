package com.app.wallpaperz.utils

import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.TwoWayConverter
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

private const val SpVisibilityThreshold = 0.1f

val TextUnit.Companion.VectorConverter: TwoWayConverter<TextUnit, AnimationVector1D>
    get() = SpToVector

val TextUnit.Companion.VisibilityThreshold: TextUnit
    get() = SpVisibilityThreshold.sp
