package com.danzucker.jetpack_compose_learning.animation

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.LinearEasing
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
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.danzucker.jetpack_compose_learning.ui.theme.Jetpack_Compose_LearningTheme

@Composable
fun InfiniteAnimationDemo(modifier: Modifier = Modifier) {
    val transition = rememberInfiniteTransition(
        label = "Infinite Transition"
    )
    val infiniteTransitionRation by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Reverse
        ),
        label = "ratio Animation"
    )

    val infiniteColor by transition.animateColor(
        initialValue = Color.Red,
        targetValue = Color.Blue,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Reverse
        ),
        label = "Infinite Color Animation",
    )

   Box(
       modifier = Modifier
           .graphicsLayer {
               rotationZ = infiniteTransitionRation * 360
               scaleX = infiniteTransitionRation
               scaleY = infiniteTransitionRation
           }
           .size(100.dp)
           //.background(infiniteColor)
           .drawBehind {
               drawCircle(
                   color = infiniteColor
               )
           }

   )
}

@Preview
@Composable
private fun InfiniteAnimationDemoPreview() {
//    Jetpack_Compose_LearningTheme {
//        InfiniteAnimationDemo()
//    }
}