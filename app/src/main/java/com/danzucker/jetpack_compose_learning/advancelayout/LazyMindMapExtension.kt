package com.danzucker.jetpack_compose_learning.advancelayout

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.draggable2D
import androidx.compose.foundation.gestures.rememberDraggable2DState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.layout.LazyLayout
import androidx.compose.foundation.lazy.layout.LazyLayoutItemProvider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntRect
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.round
import androidx.compose.ui.util.fastForEach
import androidx.compose.ui.util.fastMapIndexedNotNull
import kotlin.math.roundToInt



data class MindMapItemExtension(
    val absoluteOffset: Offset,
    val constraints: Constraints,
    val content: @Composable () -> Unit,
)

data class ProcessedMindMapItemExtension(
    val placeable: Placeable,
    val finalXPosition: Int,
    val finalYPosition: Int
)



@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LazyMindMapExtension(
    items: List<MindMapItemExtension>,
    mindMapOffset: IntOffset = IntOffset.Zero,
    onDrag: (delta: IntOffset) -> Unit,
    itemModifier: Modifier = Modifier,
    modifier: Modifier = Modifier
) {
    LazyLayout(
        modifier = modifier
            .draggable2D(
                state = rememberDraggable2DState { delta ->
                    onDrag(delta.round())
                }
            ),
        itemProvider = {
            object : LazyLayoutItemProvider {
                override val itemCount: Int
                    get() = items.size

                @Composable
                override fun Item(index: Int, key: Any) {
                    Box(
                        modifier = itemModifier
                            .border(
                                width = 2.dp,
                                color = Color.LightGray
                            )
                            .padding(16.dp)
                    ) {
                        items[index].content()
                    }
                }
            }
        }
    ) { constraints ->
        val layoutWidth = constraints.maxWidth
        val layoutHeight = constraints.maxHeight

        val visibleArea = IntRect(
            left = 0,
            top = 0,
            right = layoutWidth,
            bottom = layoutHeight
        )

        val visibleItems = items.fastMapIndexedNotNull { index, item ->
            val finalXPosition = (item.absoluteOffset.x + mindMapOffset.x).roundToInt()
            val finalYPosition = (item.absoluteOffset.y + mindMapOffset.y).roundToInt()

            val maxItemWidth = item.constraints.maxWidth
            val maxItemHeight = item.constraints.maxHeight

            val extendedItemBounds = IntRect(
                left = finalXPosition - maxItemWidth / 2,
                top = finalYPosition - maxItemHeight / 2,
                right = finalXPosition + 3 * (maxItemWidth / 2),
                bottom = finalYPosition + 3 * (maxItemHeight / 2),
            )

            if (visibleArea.overlaps(extendedItemBounds)) {
                val placeable = measure(
                    index = index,
                    constraints = item.constraints
                ).first()

                ProcessedMindMapItemExtension(
                    placeable = placeable,
                    finalXPosition = finalXPosition,
                    finalYPosition = finalYPosition,
                )
            } else null
        }
        layout(
            constraints.maxWidth,
            constraints.maxHeight
        ) {
            visibleItems.fastForEach { processedMindMapItem ->
                processedMindMapItem.placeable.place(
                    x = processedMindMapItem.finalXPosition - processedMindMapItem.placeable.width / 2,
                    y = processedMindMapItem.finalYPosition - processedMindMapItem.placeable.height / 2
                )
            }
        }
    }
}