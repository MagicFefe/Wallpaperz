package com.app.wallpaperz.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.wallpaperz.repository.WallpapersRepository
import com.app.wallpaperz.ui.screens.home.HomeScreenViewModel

class HomeScreenViewModelFactory: ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeScreenViewModel(WallpapersRepository()) as T
    }
}
