package com.app.wallpaperz.ui.screens.home

import android.app.WallpaperManager
import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.app.wallpaperz.R
import com.app.wallpaperz.ui.components.CollapsingTopAppBar
import com.app.wallpaperz.ui.theme.DarkGray
import com.app.wallpaperz.ui.theme.Gray
import com.app.wallpaperz.utils.HomeScreenViewModelFactory
import com.app.wallpaperz.utils.rememberInfiniteAnimatedVectorPainter
import com.app.wallpaperz.utils.rememberWallpaperManager
import com.app.wallpaperz.utils.setResource

@OptIn(
    ExperimentalFoundationApi::class, ExperimentalMaterialApi::class,
    ExperimentalCoilApi::class, ExperimentalAnimationGraphicsApi::class
)
@Composable
fun HomeScreen(
    homeScreenViewModel: HomeScreenViewModel = viewModel(
        modelClass = HomeScreenViewModel::class.java,
        factory = HomeScreenViewModelFactory()
    ),
    wallpaperManager: WallpaperManager = rememberWallpaperManager()
) {
    val context = LocalContext.current
    val listState = rememberLazyListState()
    val scrolledToTop = listState.firstVisibleItemIndex == 0
            && listState.firstVisibleItemScrollOffset == 0
    val wallpapers = homeScreenViewModel.wallpapers
    Scaffold(
        topBar = {
            CollapsingTopAppBar(
                title = stringResource(R.string.home_title_top_app_bar),
                scrolledToTop = scrolledToTop
            )
        }
    ) {
        LazyVerticalGrid(
            state = listState,
            modifier = Modifier.fillMaxWidth(),
            cells = GridCells.Fixed(2),
        ) {
            wallpapers.forEach { wallpaper ->
                item {
                    Card(
                        modifier = Modifier
                            .width(155.dp)
                            .height(300.dp)
                            .padding(8.dp),
                        shape = RoundedCornerShape(6.dp),
                        onClick = {
                            wallpaperManager.setResource(
                                context,
                                wallpaper.resourceId,
                                WallpaperManager.FLAG_SYSTEM
                            )
                            wallpaperManager.setResource(
                                context,
                                wallpaper.resourceId,
                                WallpaperManager.FLAG_LOCK
                            )
                        },
                        role = Role.Button
                    ) {
                        val painter = rememberImagePainter(wallpaper.resourceId)
                        Image(
                            painter = painter,
                            contentScale = ContentScale.Crop,
                            contentDescription = null
                        )
                        if (painter.state !is ImagePainter.State.Success) {
                            Box(
                                modifier = Modifier.background(DarkGray)
                            ) {
                                Icon(
                                    modifier = Modifier.align(Alignment.Center),
                                    painter = rememberInfiniteAnimatedVectorPainter(
                                        id = R.drawable.mountains_animated,
                                        duration = 1200
                                    ),
                                    contentDescription = null,
                                    tint = Gray
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun HomeScreen_Preview() {
    HomeScreen()
}
