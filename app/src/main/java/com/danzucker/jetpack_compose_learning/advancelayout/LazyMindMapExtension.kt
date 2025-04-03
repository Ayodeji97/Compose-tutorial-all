package com.danzucker.jetpack_compose_learning.advancelayout

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.draggable2D
import androidx.compose.foundation.gestures.rememberDraggable2DState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.layout.LazyLayout
import androidx.compose.foundation.lazy.layout.LazyLayoutItemProvider
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntRect
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.round
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.fastForEach
import androidx.compose.ui.util.fastMapIndexedNotNull
import com.danzucker.jetpack_compose_learning.ui.theme.Jetpack_Compose_LearningTheme
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
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        items[index].content()
                    }
                }
            }
        }
    ) { constraints ->
        val layoutWidth = constraints.maxWidth
        val layoutHeight = constraints.maxHeight

        val centerX = layoutWidth / 2
        val centerY = layoutHeight / 2

        val visibleArea = IntRect(
            left = 0,
            top = 0,
            right = layoutWidth,
            bottom = layoutHeight
        )

        val visibleItems = items.fastMapIndexedNotNull { index, item ->
            val finalXPosition = (item.absoluteOffset.x + mindMapOffset.x + centerX).roundToInt()
            val finalYPosition = (item.absoluteOffset.y + mindMapOffset.y + centerY).roundToInt()

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



@Composable
fun SimpleTodoItem(
    modifier: Modifier = Modifier
) {

    var checked by remember {
        mutableStateOf(false)
    }

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            Text(
                text = "Mind map todo item",
            )

            Text(
                text = "Mind map todo description",
            )
        }

        Checkbox(
            checked = checked,
            onCheckedChange = {
                checked = it
            }
        )
    }
}

@Composable
fun CounterComposable(
    modifier: Modifier = Modifier
) {
    var count by remember {
        mutableIntStateOf(0)
    }
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = count.toString(),
            fontSize = 40.sp,
            fontWeight = FontWeight.SemiBold
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = {
                count--
            }) {
                Text("Dec")
            }

            Button(onClick = {
                count++
            }) {
                Text("Inc")
            }
        }
    }
}

@Preview
@Composable
private fun CounterComposablePreview() {
    Jetpack_Compose_LearningTheme {
        CounterComposable()
    }
}



@Preview
@Composable
private fun SimpleTodoItemPreview() {
    Jetpack_Compose_LearningTheme {
        SimpleTodoItem(
            modifier = Modifier

        )
    }
}
