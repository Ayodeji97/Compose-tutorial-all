package com.danzucker.jetpack_compose_learning.advancelayout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.danzucker.jetpack_compose_learning.ui.theme.Jetpack_Compose_LearningTheme

/**
 * There are three phases in Jetpack Compose:
 * 1. The composition phase - WHAT NEEDS TO BE DRAWN
 * 2. The layout phase - WHERE TO DRAW
 * 3. The drawing phase - HOW TO DRAW
 *
 *
 * THE LAYOUT PHASE: The layout phase deals wit where things should be drawn on the screen.
 * This has to do with the size and position of the elements on the screen. This has 3 phases:
 * 1. Measure -
 *  a. Measures it's children - This is where the size of the elements are calculated.
 * 2 Measure - Measures it's self - This is where the size of the parent is calculated. How is this calculated?
 *      This is based on the child with the highest width and the sum of all the children heights.
 * 3. Placement - Determine the final position of the children. And forward this information to the drawing phase.
 *
 */

@Composable
fun MeasurementDemo(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(Color.Red)
            .padding(16.dp)
    ) {
       Text(
           text = "Measurement Demo One",
           modifier = Modifier
               .background(Color.Green)
       )

        Text(
            text = "Measurement Demo Two and I want to be longer and even longer",
            modifier = Modifier
                .background(Color.Yellow)
        )
    }
}

@Preview(
    showBackground = true
)
@Composable
private fun MeasurementDemoPreview() {
    Jetpack_Compose_LearningTheme {
        MeasurementDemo()
    }
}