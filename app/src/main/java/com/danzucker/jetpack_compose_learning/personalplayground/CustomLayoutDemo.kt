package com.danzucker.jetpack_compose_learning.personalplayground

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.danzucker.jetpack_compose_learning.ui.theme.Jetpack_Compose_LearningTheme

@Composable
fun CustomLayoutDemo(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {

    Layout(
        content = content,
        modifier = modifier
    ) { measurables, constraints ->
        /**
         * Step 1: Measurement step : Determine the size of component - MEASUREMENT SCOPE
         * Step 2: Placement step : Determine the position of the component - PLACEMENT SCOPE
         */

        // Single pass measurement - Meaning that a layout element may not measure any of 's children more than once, good for performance
        val placeables = measurables.map {
            it.measure(constraints)
        }

        layout(constraints.maxWidth, constraints.maxHeight) {
            /**
             * To start placing the children, we need to determine the x and y coordinates
             */
            val xPosition = 0
            val yPositon = 0

//            placeables.forEach {
//                it.place(0, 0)
//            }
            placeables.map {
                it.place(
                    x = xPosition,
                    y = yPositon
                )
            }
        }
    }
}


@Composable
private fun CustomSingleLayoutDemo(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(40.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),

    ) {
        SimpleBoxDemo()
        SimpleBoxDemo()
        /**
         * I want this item to rebel against the parent layout and take up the entire width
         */
        SimpleBoxDemo(
            modifier = Modifier
                .layout { measurable, constraints ->
                    // measurement
                    val placeable = measurable.measure(
                        constraints.copy(
                            maxWidth = constraints.maxWidth + 80.dp.roundToPx()
                        )
                    )
                    // placement
                    layout(placeable.width, placeable.height) {
                        placeable.place(0, 0)
                    }
                }
        )
        SimpleBoxDemo()
        SimpleBoxDemo()
    }
}

@Composable
fun SimpleBoxDemo(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .width(200.dp)
            .height(20.dp)
            .background(Color.Red),
    )
}

/**
 * A case study where we want all children in a layout to have
 * the width of the widest child, we can use intrinsic measurement
 * to get the width of the widest child and then set the width of the layout to that width
 */
@Composable
fun IntrinsicMeasurement() {
    Column(
      modifier = Modifier
          .background(Color.LightGray)
          .width(IntrinsicSize.Min)
    ) {
        Text(
            text = "First text",
            modifier = Modifier
                .background(Color.Cyan)
        )

        Text(
            text = "Second text, same here",
            modifier = Modifier
                .background(Color.Blue)
        )

        Text(
            text = "Third text, i want to be long",
            modifier = Modifier
                .background(Color.Yellow)

        )

        Text(
            text = "Fourth text, just long",
            modifier = Modifier
                .background(Color.Green)
        )

        Text(
            text = "Fifth text, a bit longer",
            modifier = Modifier
                .background(Color.Red)
        )
    }
    
}

@Preview
@Composable
private fun CustomLayoutDemoPreview() {
    Jetpack_Compose_LearningTheme {
        CustomLayoutDemo(
            content = {
                    Box(
                        modifier = Modifier
                            .size(100.dp)
                            .background(Color.Red)
                    )
            }
        )
    }
}

@Preview
@Composable
private fun CustomSingleLayoutDemoPreview() {
    Jetpack_Compose_LearningTheme {
        //CustomSingleLayoutDemo()
        //SimpleBoxDemo()
        IntrinsicMeasurement()
    }
}

