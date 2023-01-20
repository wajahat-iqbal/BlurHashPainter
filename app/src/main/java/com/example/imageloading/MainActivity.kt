package com.example.imageloading

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels

import androidx.compose.foundation.layout.*

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.*
import androidx.compose.runtime.*

import androidx.compose.ui.Modifier
import androidx.compose.ui.composed

import androidx.compose.ui.graphics.Color

import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext

import androidx.compose.ui.unit.dp

import coil.compose.AsyncImage

import coil.request.ImageRequest

import com.example.imageloading.domain.domain_model.ImagesDomainModel
import com.example.imageloading.presentation.BlurHashPainter
import com.example.imageloading.presentation.ImageLoadingState
import com.example.imageloading.presentation.ImageViewModel
import com.example.imageloading.ui.theme.ImageLoadingTheme
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.shimmer


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: ImageViewModel by viewModels()
        setContent {
            ImageLoadingTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = LightPH
                ) {
                    LaunchedEffect(key1 = "", block = {
                        viewModel.getImages()
                    })
                    val imageDataSate = viewModel.mutableState.collectAsState()


                    when (imageDataSate.value) {
                        is ImageLoadingState.Idle -> {

                        }
                        is ImageLoadingState.Loading -> {

                            Column(modifier = Modifier.padding(5.dp)) {
                                repeat(10) {
                                    ItemCard(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(200.dp)
                                    )
                                }
                            }

                        }
                        is ImageLoadingState.Success -> {
                            val imageLoadingDomainModel =
                                (imageDataSate.value as ImageLoadingState.Success).data
                            Screen(
                                imageLoadingDomainModel
                            ) {
                                viewModel.getImages()
                            }
                        }
                        is ImageLoadingState.Error -> {

                        }
                    }
                }

            }
        }
    }
}

@Composable
fun Screen(imageDomainModel: List<ImagesDomainModel>, onReload: () -> Unit) {

    LazyVerticalGrid(columns = GridCells.Fixed(3), state = rememberLazyGridState(), content = {
        itemsIndexed(imageDomainModel) { index, item ->
            Card(modifier = Modifier.padding(5.dp)) {
                AsyncImage(
                    contentDescription = "",
                    modifier = Modifier
                        .height(100.dp)
                        .width(100.dp),
                    model = ImageRequest.Builder(
                        LocalContext.current
                    ).data(item.original.src).crossfade(true).build(),
                    placeholder = BlurHashPainter(
                        item.blurHash,
                        item.landscape.width,
                        item.landscape.height,
                        0.7F,
                        0.3F
                    ),
                    contentScale = ContentScale.FillBounds,
                )
            }

        }
    })

}


@Composable
private fun ItemCard(
    modifier: Modifier,
) {
    Card(modifier = modifier.padding(5.dp)) {
        Text(
            "", modifier = modifier
                .placeHolder(LightPH)
                .fillMaxSize()
        )
    }

}


val DarkGrey = Color(0x223700B3)
val LightPH = Color(0xFFE6E6E6)


fun Modifier.placeHolder(color: Color): Modifier = composed {
    placeholder(
        visible = true, color = color, highlight = PlaceholderHighlight.shimmer(DarkGrey)
    )
}
