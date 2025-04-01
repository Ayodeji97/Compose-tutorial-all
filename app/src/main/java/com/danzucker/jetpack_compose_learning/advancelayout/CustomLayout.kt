package com.danzucker.jetpack_compose_learning.advancelayout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEach
import androidx.compose.ui.util.fastMaxOfOrNull
import com.danzucker.jetpack_compose_learning.ui.theme.Jetpack_Compose_LearningTheme

/**
 * PagedRow is a custom layout that lays out its children in a row to fill in the available width or
 * move to the next page if the width is not enough and the page is not exceeded.
 */
@Composable
fun PagedRow(
    page: Int,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {

    Layout(
        content = content,
        modifier = modifier
    ) { measurables, constraints ->
        val placeables = measurables.map {
            it.measure(constraints)
        }

        val pages = mutableListOf<List<Placeable>>()
        var currentPage = mutableListOf<Placeable>()
        var currentPageWidth = 0

        placeables.fastForEach { placeable ->
            if (currentPageWidth + placeable.width > constraints.maxWidth) {
                pages.add(currentPage)
                currentPage = mutableListOf()
                currentPageWidth = 0
            }
            currentPage.add(placeable)
            currentPageWidth += placeable.width
        }

        if (currentPage.isNotEmpty()) {
            pages.add(currentPage)
        }

        val pageItems = pages.getOrNull(page) ?: emptyList()
        val maxHeight = pageItems.fastMaxOfOrNull { it.height } ?: 0

        layout(
            width = constraints.maxWidth,
            height = maxHeight
        ) {
            var xOffset = 0
            pageItems.fastForEach { placeable ->
                placeable.place(
                    x = xOffset,
                    y = 0
                )
                xOffset += placeable.width
            }
        }
    }
}

@Preview
@Composable
private fun PagedRowPreview() {
    Jetpack_Compose_LearningTheme {
        PagedRow(
            page = 2
        ) {
            Box(
                modifier = Modifier
                    .width(300.dp)
                    .height(100.dp)
                    .background(Color.Red)
            )

            Box(
                modifier = Modifier
                    .width(50.dp)
                    .height(150.dp)
                    .background(Color.Yellow)
            )

            Box(
                modifier = Modifier
                    .width(150.dp)
                    .height(100.dp)
                    .background(Color.Green)
            )

            Box(
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp)
                    .background(Color.Blue)
            )

            Box(
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp)
                    .background(Color.Cyan)
            )

            Box(
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp)
                    .background(Color.Magenta)
            )


            Box(
                modifier = Modifier
                    .width(75.dp)
                    .height(100.dp)
                    .background(Color.DarkGray)
            )


            Box(
                modifier = Modifier
                    .width(75.dp)
                    .height(100.dp)
                    .background(Color.Black)
            )
        }
    }
}