package com.danzucker.jetpack_compose_learning.advancelayout.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEach
import androidx.compose.ui.util.fastMaxOfOrNull
import com.danzucker.jetpack_compose_learning.advancelayout.PagedRow
import com.danzucker.jetpack_compose_learning.ui.theme.Jetpack_Compose_LearningTheme

/**
 * While the normal layout phase of a normal layout is - measure children -> measure itself -> place children -> layout itself
 * SubcomposeLayout is a special layout that allows you to measure and layout children in a custom way. -
 * In the subcompose phase, Measure children -> subcompose -> Measure the layout itself -> place children -> layout itself
 */

@Composable
fun SubComposePagedRow(
    page: Int,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {

    SubcomposeLayout (
        modifier = modifier
    ) { constraints ->

        val pages = mutableListOf<List<Placeable>>()
        var currentPage = mutableListOf<Placeable>()
        var currentPageWidth = 0


        val measurables = subcompose(
            slotId = "content",
            content = content
        )

        var counter = 0
        for (measurable in measurables) {
            val placeable = measurable.measure(constraints)
            counter++
            if (currentPageWidth + placeable.width > constraints.maxWidth) {
                if (pages.size == page) {
                    break
                }
                pages.add(currentPage)
                currentPage = mutableListOf()
                currentPageWidth = 0
            }
            currentPage.add(placeable)
            currentPageWidth += placeable.width
        }

        println("We measured $counter composables")


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
private fun SubComposePagedRowPreview() {
    Jetpack_Compose_LearningTheme {
        PagedRow(
            page = 0
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
                    .width(75.dp)
                    .height(100.dp)
                    .background(Color.Green)
            )

            Box(
                modifier = Modifier
                    .width(300.dp)
                    .height(100.dp)
                    .background(Color.Blue)
            )
        }
    }
}