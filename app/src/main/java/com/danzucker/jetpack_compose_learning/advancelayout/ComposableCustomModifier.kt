package com.danzucker.jetpack_compose_learning.advancelayout

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.MeasureResult
import androidx.compose.ui.layout.MeasureScope
import androidx.compose.ui.layout.layout
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode
import androidx.compose.ui.node.LayoutModifierNode
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.node.currentValueOf
import androidx.compose.ui.node.invalidateMeasurement
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.danzucker.jetpack_compose_learning.ui.theme.Jetpack_Compose_LearningTheme


fun Modifier.improveNegativePadding(horizontal: Dp): Modifier {
    return this.then(NegativePaddingModifier(horizontal))
}

// To apply the node, we need another class
data class NegativePaddingModifier(
    private val horizontal: Dp
) : ModifierNodeElement<NegativePaddingNode>() {
    override fun create(): NegativePaddingNode {
        return NegativePaddingNode(horizontal)
    }

    override fun update(node: NegativePaddingNode) {
        node.horizontal = horizontal
    }
}


class NegativePaddingNode(
    var horizontal: Dp
) : LayoutModifierNode, Modifier.Node(), CompositionLocalConsumerModifierNode {

    override fun MeasureScope.measure(
        measurable: Measurable,
        constraints: Constraints
    ): MeasureResult {
        val density  = currentValueOf(LocalDensity)
        val px = with(density) {
            horizontal.roundToPx()
        }
        val placeable = measurable.measure(
            constraints = constraints.copy(
                minWidth = constraints.minWidth + 2 * px,
                maxWidth = constraints.maxWidth + 2 * px
            )
        )

       return layout(placeable.width, placeable.height) {
            placeable.place(
                x = 0,
                y = 0
            )
        }
    }
}


/**
 * There are some problem with solution
 * 1. The use of local density which is like a global variable can leads to some inconsistency
 * 2. When a compose function return a value, it will never be skipped, that is it will always be recomposed
 * even though it's state doesn't change
 *
 * 3. This function can only be use only inside a composable function.
 */
@Composable
fun Modifier.negativePadding(horizontal: Dp): Modifier {
    val density = LocalDensity.current
    val px = with(density) {
        horizontal.roundToPx()
    }
    return layout { measurable, constraints ->
        val placeable = measurable.measure(
            constraints = constraints.copy(
                minWidth = constraints.minWidth + 2 * px,
                maxWidth = constraints.maxWidth + 2 * px
            )
        )

        layout(placeable.width, placeable.height) {
            placeable.place(
                x = 0,
                y = 0
            )
        }
    }
}




@Composable
fun MyList(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = "This is item 1",
            modifier = Modifier
                .clickable {

                }
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        )
        HorizontalDivider(
            modifier = Modifier
                .improveNegativePadding(16.dp)
        )
        Text(
            text = "This is another item",
            modifier = Modifier
                .clickable {

                }
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        )
        HorizontalDivider(
            modifier = Modifier
                .improveNegativePadding(16.dp)
        )
        Text(
            text = "And another one",
            modifier = Modifier
                .clickable {

                }
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Button(
                onClick = {}
            ) {
                Text("Button 1")
            }
            Button(
                onClick = {}
            ) {
                Text("Button 2")
            }
            Button(
                onClick = {}
            ) {
                Text("Button 3")
            }
        }
    }
}

@Preview
@Composable
private fun MyListPreview() {
    Jetpack_Compose_LearningTheme {
        MyList()
    }
}