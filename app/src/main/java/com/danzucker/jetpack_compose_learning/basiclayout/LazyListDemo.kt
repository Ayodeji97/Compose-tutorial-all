@file:OptIn(ExperimentalFoundationApi::class)

package com.danzucker.jetpack_compose_learning.basiclayout

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.danzucker.jetpack_compose_learning.ui.theme.Jetpack_Compose_LearningTheme
import kotlin.random.Random

@Composable
fun LazyListDemo(modifier: Modifier = Modifier) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = spacedBy(16.dp)
    ) {

        // Here we have the items composable
        items(100) { index ->
            Text("Item $index")
        }

        stickyHeader {
            Text(
                text = "Header - A",
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Gray)
            )
        }


        items(100) { index ->
            Text("Item ${index + 100}")
        }

        stickyHeader {
            Text(
                text = "Header - B",
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Gray)
            )
        }


        items(100) { index ->
            Text("Item ${index + 200}")
        }

        // We have the item composable as well
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Red)
            ) {
                Text("Reach the end of the list")
            }
        }
    }


}

@Composable
fun LazyListRow(modifier: Modifier = Modifier) {
    LazyRow(
        modifier = Modifier
            .fillMaxSize()
    ) {
        items(100) {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .background(Color(Random.nextInt()))
            )
        }
    }
}

@Preview
@Composable
private fun LazyListDemoPreview() {
    Jetpack_Compose_LearningTheme {
        LazyListDemo()
    }
}

@Preview
@Composable
private fun LazyRowDemoPreview() {
    Jetpack_Compose_LearningTheme {
        LazyListRow()
    }
}