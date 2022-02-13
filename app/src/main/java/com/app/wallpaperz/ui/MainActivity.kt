package com.app.wallpaperz.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.app.wallpaperz.ui.screens.home.HomeScreen
import com.app.wallpaperz.ui.theme.WallpaperzTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WallpaperzTheme {
                HomeScreen()
            }
        }
    }
}




