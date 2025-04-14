package com.danzucker.jetpack_compose_learning.performanceoptimization

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.danzucker.jetpack_compose_learning.R
import com.danzucker.jetpack_compose_learning.ui.theme.Jetpack_Compose_LearningTheme

@Composable
fun ImageLoading(modifier: Modifier = Modifier) {

    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(100) {
//            Image(
//                painter = painterResource(R.drawable.simple_cat_image),
//                contentDescription = null,
//                modifier = Modifier
//                    .fillParentMaxHeight(0.5f)
//                    .fillParentMaxWidth(),
//                contentScale = ContentScale.Crop
//            )

            // Use asyncImage from Coil to load images from the internet
            AsyncImage(
                model = "https://images.unsplash.com/photo-1615497001839-b0a0eac3274c?w=900&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTZ8fGN1dGUlMjBjYXR8ZW58MHx8MHx8fDA%3D",
                contentDescription = null,
                modifier = Modifier
                    .fillParentMaxHeight(0.5f)
                    .fillParentMaxWidth(),
                contentScale = ContentScale.Crop
            )
        }
    }
}


@Preview
@Composable
private fun ImageLoadingPreview() {
    Jetpack_Compose_LearningTheme {
        ImageLoading()
    }
}