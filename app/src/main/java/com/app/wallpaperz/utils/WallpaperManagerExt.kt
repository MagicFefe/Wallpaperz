package com.app.wallpaperz.utils

import android.app.WallpaperManager
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.view.WindowManager
import androidx.annotation.DrawableRes
import androidx.annotation.IntRange
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlin.coroutines.suspendCoroutine

@Suppress("DEPRECATION")
fun WallpaperManager.setResource(
    context: Context,
    @DrawableRes resourceId: Int,
    @IntRange(from = 1, to = 2) which: Int,
    scope: CoroutineScope = MainScope()
) {
    scope.launch(Dispatchers.IO) {
        suspendCoroutine {
            val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val screenWidth: Int
            val screenHeight: Int
            if (Build.VERSION.SDK_INT > 29) {
                screenHeight = windowManager.currentWindowMetrics.bounds.height()
                screenWidth = windowManager.currentWindowMetrics.bounds.width()
            } else {
                screenWidth = windowManager.defaultDisplay.width
                screenHeight = windowManager.defaultDisplay.height
            }
            val decodedDrawableToBitmap =
                BitmapFactory.decodeResource(context.resources, resourceId)
            val scaledBitmap = Bitmap.createScaledBitmap(
                decodedDrawableToBitmap,
                screenWidth,
                screenHeight,
                true
            )
            setBitmap(
                scaledBitmap,
                null,
                true,
                which
            )
        }
    }
}
