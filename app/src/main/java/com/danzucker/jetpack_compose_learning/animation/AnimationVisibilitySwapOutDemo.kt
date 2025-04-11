package com.danzucker.jetpack_compose_learning.animation


import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.togetherWith
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.danzucker.jetpack_compose_learning.ui.theme.Jetpack_Compose_LearningTheme

@Composable
fun AnimationVisibilitySwapOutDemo(modifier: Modifier = Modifier) {
    var toggle by remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Button(
            onClick = {
                toggle = !toggle
            },
        ) {
            Text("Toggle")
        }

        AnimatedContent(
            targetState = toggle,
            label = "",
            transitionSpec = {
                (slideIntoContainer(
                    towards = if (toggle) {
                        AnimatedContentTransitionScope.SlideDirection.Right
                    } else {
                        AnimatedContentTransitionScope.SlideDirection.Left
                    },
                    animationSpec = tween(durationMillis = 4000)
                )) togetherWith slideOutOfContainer(
                    towards = if (toggle) {
                        AnimatedContentTransitionScope.SlideDirection.Right
                    } else {
                        AnimatedContentTransitionScope.SlideDirection.Left
                    },
                    animationSpec = tween(durationMillis = 4000)
                )
            }
        ) { toggle ->
            if (toggle) {
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
            } else {
                Text(
                    text = "Bye bye world",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .padding(16.dp)
                        .border(
                            width = 5.dp,
                            color = Color.Green
                        )
                        .wrapContentSize()
                )
            }
        }

    }
}

@Preview
@Composable
private fun AnimationVisibilitySwapOutDemoPreview() {
    Jetpack_Compose_LearningTheme {
        AnimationVisibilitySwapOutDemo()
    }
}