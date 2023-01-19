package com.example.imageloading

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.imageloading.domain.domain_model.ImagesDomainModel
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
                    modifier = Modifier.fillMaxSize(),
                    color = LightPH
                ) {
                    LaunchedEffect(key1 = "", block = {
                        viewModel.getImages()
                    })
                    val imageDataSate = viewModel.mutableState.collectAsState()

                    LazyColumn(
                        state = rememberLazyListState(),
                        content = {
                            when (imageDataSate.value) {
                                is ImageLoadingState.Idle -> {

                                }
                                is ImageLoadingState.Loading -> {
                                    item {
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
                                }
                                is ImageLoadingState.Success -> {
                                    val imageLoadingDomainModel = (imageDataSate.value as ImageLoadingState.Success).data
                                    item {
                                        TopAppBar(elevation = 0.dp, title = {
                                            Column{
                                                Text(
                                                    text = "Network Time : ${imageLoadingDomainModel.networkCallTime}",
                                                    style = MaterialTheme.typography.h6.copy(
                                                        fontSize = 15.sp
                                                    ),
                                                    textAlign = TextAlign.Start,
                                                    modifier = Modifier.fillMaxWidth()
                                                )
                                                Text(
                                                    text = "BlurHash to ImageBitmap Time : ${imageLoadingDomainModel.blurHashToImageBitmapTime} ",
                                                    style = MaterialTheme.typography.h6.copy(
                                                        fontSize = 15.sp
                                                    ),
                                                    textAlign = TextAlign.Start,
                                                    modifier = Modifier.fillMaxWidth()
                                                )

                                            }
                                        }, backgroundColor = LightPH, navigationIcon = {
                                            IconButton(onClick = { viewModel.getImages() }) {
                                                Image(
                                                    painter = painterResource(id = R.drawable.ic_baseline_replay_24),
                                                    contentDescription = null,
                                                    modifier = Modifier.offset(x = (4.dp))
                                                )
                                            }
                                        })
                                    }
                                    imageCards(imageLoadingDomainModel.images)
                                }
                                is ImageLoadingState.Error -> {

                                }
                            }

                        })

                }
            }
        }
    }
}

fun LazyListScope.imageCards(list: List<ImagesDomainModel>) {
    itemsIndexed(list) { index, item ->
        Card(modifier = Modifier.padding(5.dp)) {
            val painter: BitmapPainter? = item.bitmap?.let { BitmapPainter(it) }
            //val painter = ColorPainter(DarkGrey)
            AsyncImage(
                contentDescription = "",
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxSize(),
                model = ImageRequest.Builder(
                    LocalContext.current
                ).data(item.original.src).crossfade(true).build(),
                 placeholder = painter,
                contentScale = ContentScale.FillWidth,
            )
        }

    }
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
        visible = true,
        color = color,
        highlight = PlaceholderHighlight.shimmer(DarkGrey)
    )
}
