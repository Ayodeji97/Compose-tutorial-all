package com.danzucker.jetpack_compose_learning.basiclayout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.danzucker.jetpack_compose_learning.ui.theme.Jetpack_Compose_LearningTheme
import kotlin.random.Random

@Composable
fun LazyGrid(modifier: Modifier = Modifier) {

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        verticalArrangement = spacedBy(16.dp),
        horizontalArrangement = spacedBy(16.dp)
    ) {
        items(100) { index ->
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color(Random.nextInt())),

                contentAlignment = Alignment.Center
            ) {
                Text("Item $index")
            }
        }
    }
}

@Preview
@Composable
private fun LazyGridPreview() {
    Jetpack_Compose_LearningTheme {
        LazyGrid()
    }
}