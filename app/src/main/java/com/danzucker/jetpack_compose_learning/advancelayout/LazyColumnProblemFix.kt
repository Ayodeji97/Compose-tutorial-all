package com.danzucker.jetpack_compose_learning.advancelayout

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.danzucker.jetpack_compose_learning.ui.theme.Jetpack_Compose_LearningTheme

@Composable
fun LazyColumnDemo(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
    ) {
        items(20) {
           Text(
               text = "Item $it",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
           )
        }

        item {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
            ) {
               items(10) {
                   Text(
                       text = "Item $it",
                       modifier = Modifier
                           .fillMaxWidth()
                           .padding(16.dp)
                   )
               }
            }
        }
    }
}

@Preview
@Composable
private fun LazyColumnDemoPreview() {
    Jetpack_Compose_LearningTheme {
        LazyColumnDemo()
    }
}