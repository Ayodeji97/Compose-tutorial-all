package com.danzucker.jetpack_compose_learning.animation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.danzucker.jetpack_compose_learning.ui.theme.Jetpack_Compose_LearningTheme

@Composable
fun AnimateListItemChangesDemo(modifier: Modifier = Modifier) {
    var items by remember {
        mutableStateOf(
            (1..100).map {
                "List Item $it"
            }
        )
    }

    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Button(
            onClick = {
                items = items.shuffled()
            }
        ) {
            Text("Shuffle")
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            items(
                items = items,
                key = { it }
            ) { text ->
                Text(
                    text = text,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .animateItem(),
                    fontSize = 20.sp
                )
            }
        }
    }
}

@Preview
@Composable
private fun AnimateListItemChangesDemoPreview() {
    Jetpack_Compose_LearningTheme {
        AnimateListItemChangesDemo()
    }
}