package com.danzucker.jetpack_compose_learning.advancelayout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.danzucker.jetpack_compose_learning.ui.theme.Jetpack_Compose_LearningTheme

@Composable
fun OverflowLayout(
    isOverflowing: Boolean,
    modifier: Modifier = Modifier,
    mainContent: @Composable () -> Unit,
    overflowContent: @Composable () -> Unit
) {

    SubcomposeLayout(
        modifier = modifier
    ) { constraints ->

        val measurables = subcompose(
            slotId = "mainContent",
            content = mainContent
        )

        val overflowMeasurables = subcompose(
            slotId = "overflowContent",
            content = overflowContent
        )

        var mainContentPlaceable: Placeable? = null
        var overflowContentPlaceable: Placeable? = null

        for (measurable in measurables) {
            mainContentPlaceable = measurable.measure(constraints)
            if (!isOverflowing) {
                break
            }
            overflowContentPlaceable = overflowMeasurables.first().measure(constraints)
        }

        layout(
            width = constraints.maxWidth,
            height = constraints.maxHeight
        ) {
            mainContentPlaceable?.place(0, 0)
            overflowContentPlaceable?.place(0, mainContentPlaceable?.height ?: 0)
        }
    }
}


@Preview
@Composable
private fun OverflowLayoutPreview() {
    Jetpack_Compose_LearningTheme {
        OverflowLayout(
            isOverflowing = true,
            mainContent = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Green)
                        .padding(16.dp)
                ) {
                    Text(
                        text = "This is a toggle slection"
                    )

                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowDown,
                            contentDescription = null
                        )
                    }
                }
            },
            overflowContent = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Yellow)
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Secret section"
                    )

                }
            }
        )
    }
}