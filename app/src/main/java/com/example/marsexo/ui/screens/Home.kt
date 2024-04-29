
package com.example.marsexo.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.marsexo.R
import com.example.marsexo.data.MarsPic
import com.example.marsexo.ui.theme.MarsPhotosTheme
import androidx.compose.animation.core.*
import androidx.compose.animation.fadeIn



// -----------------------------> HEADER composables
@Composable
fun HomeScreen(
    marsUiState: MarsUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    Column(modifier = modifier.fillMaxSize()) {
        // Display introductory image MARS SPACE
        IntroductoryImage(modifier = Modifier.fillMaxWidth())

        // New section for text
        TextSection()

        // the lazy grid --->
        when (marsUiState) {
            is MarsUiState.Loading -> LoadingScreen(modifier = Modifier.fillMaxSize())
            is MarsUiState.Success -> PhotosGridScreen(
                marsUiState.photos, contentPadding = contentPadding, modifier = Modifier.fillMaxWidth()
            )
            is MarsUiState.Error -> ErrorScreen(retryAction, modifier = Modifier.fillMaxSize())
        }
    }
}

@Composable
fun IntroductoryImage(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(400.dp)
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(R.drawable.marspics2),
            contentDescription = stringResource(R.string.introductory_image_description),
            contentScale = ContentScale.Crop
        )
        Text(
            text = "Welcome!",
            color = Color.White,
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Serif,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp)
        )

    }
}

@Composable
fun TextSection(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Explore Mars Rover Photos \uD83D\uDE80",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
        )
        Text(
            text = "Discover images coming from a Mars API.",
            fontSize = 16.sp,
            color = Color.White,
        )
    }
}
// -----------------------------> HEADER composables

// -----------------------------> Starter composables
@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.loading_img),
        contentDescription = stringResource(R.string.loading)
    )
}

@Composable
fun ErrorScreen(retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error), contentDescription = ""
        )
        Text(text = stringResource(R.string.loading_failed), modifier = Modifier.padding(16.dp))
        Button(onClick = retryAction) {
            Text(stringResource(R.string.retry))
        }
    }
}
// ----------------------------->  Starter composables

@Composable
fun PhotosGridScreen(
    photos: List<MarsPic>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(500.dp),
        modifier = modifier.padding(horizontal = 4.dp),
        contentPadding = contentPadding,
    ) {
        items(items = photos, key = { photo -> photo.id }) { photo ->
            AnimatedVisibility(
                visible = true,
                enter = fadeIn(
                    animationSpec = tween(
                        durationMillis = 500,
                        easing = LinearOutSlowInEasing
                    )
                )
            ) {
                MarsPhotoCard(
                    photo,
                    modifier = modifier
                        .padding(4.dp)
                        .fillMaxWidth()
                        .aspectRatio(1.5f)
                )
            }
        }
    }
}

@Composable
fun MarsPhotoCard(photo: MarsPic, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.padding(4.dp),
        shape = MaterialTheme.shapes.medium,
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current).data(photo.imgSrc)
                .crossfade(true).build(),
            error = painterResource(R.drawable.ic_broken_image),
            placeholder = painterResource(R.drawable.loading_img),
            contentDescription = stringResource(R.string.mars_photo),
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}


@Preview(showBackground = true)
@Composable
fun LoadingScreenPreview() {
    MarsPhotosTheme {
        LoadingScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorScreenPreview() {
    MarsPhotosTheme {
        ErrorScreen({})
    }
}

@Preview(showBackground = true)
@Composable
fun PhotosGridScreenPreview() {
    MarsPhotosTheme {
        val mockData = List(10) { MarsPic("$it", "") }
        PhotosGridScreen(mockData)
    }
}
