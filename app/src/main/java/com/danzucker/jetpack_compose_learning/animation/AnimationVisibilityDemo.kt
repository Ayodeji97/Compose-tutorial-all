package com.danzucker.jetpack_compose_learning.animation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.EaseInBounce
import androidx.compose.animation.core.EaseInElastic
import androidx.compose.animation.core.EaseInExpo
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.danzucker.jetpack_compose_learning.ui.theme.Jetpack_Compose_LearningTheme

/**
 * Different type of animationSpec:
 * 1. tween
 * 2. spring
 * 3. keyframes
 * 4. repeatable
 * 5. infiniteRepeatable
 * 6. snap
 *
 */

@Composable
fun AnimationVisibilityDemo(modifier: Modifier = Modifier) {
    var toggle by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        Button(
            onClick = {
                toggle = !toggle
            }
        ) {
            Text("Toggle")
        }

        val easing = LinearEasing
        AnimatedVisibility(
            visible = toggle,
            enter = scaleIn(
                animationSpec = keyframes {
                    durationMillis = 5000
                    0.3f at 2500 using EaseInElastic
                    0.5f at 2000 using EaseInBounce
                    0.8f at 3500 using EaseInExpo
                }
            ),
            exit = scaleOut(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                )
            )
        ) {
            Text(
                text = "Hello World",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(16.dp)
                    .border(
                        width = 5.dp,
                        color = Color.Red
                    )
                    .wrapContentSize()
            )
        }
    }
}

@Preview
@Composable
private fun AnimationVisibilityDemoPreview() {
    Jetpack_Compose_LearningTheme {
        AnimationVisibilityDemo()
    }
}