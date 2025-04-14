package com.danzucker.jetpack_compose_learning.animation

import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import kotlin.math.sqrt

@Composable
fun TriangleAnimationAssignment(
    dynamicWidth: Dp,
    colors: List<Color>,
    modifier: Modifier = Modifier
) {
    val height = calculateHeightFromWidth(dynamicWidth)
    val transition = rememberInfiniteTransition(
        label = "triangle animation"
    )
    val animationDuration = 5000
    val easing = EaseInOut

    Box(
        modifier = modifier
    ) {
        colors.forEachIndexed { index, color ->
            val rotationDegree by transition.animateFloat(
                initialValue = 0f,
                targetValue = 360f * (index + 1),
                animationSpec = infiniteRepeatable(
                    animation = tween(
                        durationMillis = animationDuration,
                        easing = easing
                    ),
                    repeatMode = RepeatMode.Reverse
                ),
                label = "rotation animation $index"
            )

            Box(
                modifier = Modifier
                    .graphicsLayer {
                        rotationZ = rotationDegree
                        transformOrigin = TransformOrigin(
                            pivotFractionX = 0.5f,
                            pivotFractionY = 0.66f
                        )
                    }
                    .size(
                        width = dynamicWidth,
                        height = height
                    )
                    .background(color = color, shape = AnimationTriangleShape)
            )
        }
    }
}


data object AnimationTriangleShape : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        return Outline.Generic(
            path = Path().apply {
                moveTo(
                    x = size.width / 2f,
                    y = 0f
                )
                lineTo(
                    x = 0f,
                    y = size.height
                )
                lineTo(
                    x = size.width,
                    y = size.height
                )
                close()
            }
        )
    }
}

@Composable
fun calculateHeightFromWidth(dynamicWidth: Dp): Dp {
    return with(LocalDensity.current) {
        val widthPx = dynamicWidth.toPx()
        val heightPx = widthPx * (sqrt(3.0) / 2).toFloat()
        heightPx.toDp()
    }
}


@Preview
@Composable
private fun AnimationTriangleAssignmentPreview() {
//    Jetpack_Compose_LearningTheme {
//        AnimationTriangleAssignment(
//            dynamicWidth = 50.dp,
//            colors = listOf(Color.Red, Color.Green, Color.Blue),
//            modifier = Modifier
//                .fillMaxSize()
//                .wrapContentSize()
//        )
//    }
}