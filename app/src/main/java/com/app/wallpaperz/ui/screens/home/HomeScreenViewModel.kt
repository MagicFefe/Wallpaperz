package com.app.wallpaperz.ui.screens.home

import androidx.lifecycle.ViewModel
import com.app.wallpaperz.repository.WallpapersRepository

class HomeScreenViewModel(
    private val wallpapersRepository: WallpapersRepository
): ViewModel() {
    val wallpapers = wallpapersRepository.wallpapers
}
