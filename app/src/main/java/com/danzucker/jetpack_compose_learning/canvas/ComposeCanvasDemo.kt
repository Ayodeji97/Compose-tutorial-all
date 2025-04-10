package com.danzucker.jetpack_compose_learning.canvas

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.tooling.preview.Preview
import com.danzucker.jetpack_compose_learning.ui.theme.Jetpack_Compose_LearningTheme

@Composable
fun ComposeCanvasDemo(
    modifier: Modifier = Modifier
) {

    // Canvas is a drawing API that allows you to draw shapes, text, and images on a canvas.
    // It is useful for creating custom graphics and animations.
    // The Canvas composable provides a drawing surface that you can use to draw your own graphics.

    // Example of using Canvas
    // Canvas(
    //     modifier = modifier
    // ) {
    //     drawRect(
    //         color = Color.Red,
    //         size = Size(100f, 100f)
    //     )
    // }

    /**
     *  The canvas is just a synthetic sugar for a Spacer with a Modifier.drawBehind, we can directly
     *  use the Modifier.drawBehind on any composable to draw on it.
     *
     *  We can use Modifier.drawBehind
     *  We also have Modifier.drawWithContent which allows us to draw on top of the content, it gives us the
     *  flexibilty of where to draw the content
     *  And we also have Modifier.drawWithCache which allows us to cache the drawing
     *
      */

    /**
    Canvas(
        modifier = modifier
            .fillMaxSize()
    ) {
        drawCircle(
            color = Color.Green
        )
    }

     */
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Red)
//            .drawBehind {
//                drawCircle(
//                    color = Color.Green,
//                    radius = 500f
//                )
//            }
//            .drawBehind {
//                withTransform(
//                    transformBlock = {
//                        this.rotate(
//                            degrees = 45f,
//                            pivot = center
//                        )
//                    },
//                    drawBlock = {
//                        drawArc(
//                            brush = Brush.verticalGradient(
//                                colors = listOf(Color.Red, Color.Blue)
//                            ),
//                            startAngle = 0f,
//                            useCenter = true,
//                            size = size.copy(200f, 200f),
//                            topLeft = center.copy(x = center.x - 100f, y = center.y - 100f),
//                            sweepAngle = 270f
//                        )
//                    }
//                )
//            }
//            .drawWithContent {
//                drawCircle(
//                    color = Color.Green,
//                )
//                drawContent()
//                drawCircle(
//                    color = Color.Blue,
//                    radius = 100f,
//                )
//            }
            .drawWithCache {
                val px = 100f
                onDrawWithContent {
                    drawCircle(
                        color = Color.LightGray
                    )
                    drawContent()
                    drawCircle(
                        color = Color.Blue,
                        radius = px
                    )

                }
            }
        ,
        contentAlignment = Alignment.Center
    ) {
        Text("My very own text")
    }
}




@Preview
@Composable
private fun ComposeCanvasDemoPreview() {
    Jetpack_Compose_LearningTheme {
        ComposeCanvasDemo()
    }
}