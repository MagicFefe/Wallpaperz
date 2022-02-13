package com.app.wallpaperz.utils

import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.TwoWayConverter
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

internal val SpToVector: TwoWayConverter<TextUnit, AnimationVector1D> = TwoWayConverter(
    convertToVector = { AnimationVector1D(it.value) },
    convertFromVector = { it.value.sp }
)
