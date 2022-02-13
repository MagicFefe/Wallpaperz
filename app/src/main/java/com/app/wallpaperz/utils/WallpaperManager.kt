package com.app.wallpaperz.utils

import android.app.WallpaperManager
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext

@Composable
@OptIn(ExperimentalFoundationApi::class)
fun rememberWallpaperManager(): WallpaperManager {
    val context = LocalContext.current
    val wallpaperManager = remember {
        mutableStateOf(WallpaperManager.getInstance(context))
    }
    return wallpaperManager.value
}
