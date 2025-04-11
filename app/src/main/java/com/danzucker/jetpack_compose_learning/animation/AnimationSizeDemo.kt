package com.danzucker.jetpack_compose_learning.animation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.unit.sp
import com.danzucker.jetpack_compose_learning.ui.theme.Jetpack_Compose_LearningTheme

@Composable
fun AnimationSizeDemo(modifier: Modifier = Modifier) {
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

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Red)
                .animateContentSize() // just add this line
                .height(
                    if (toggle) 400.dp else 200.dp
                )

        )

        Text(
            text = "My little text",
            fontSize = 18.sp
        )
    }
}

@Preview
@Composable
fun AnimationSizeDemoPreview(modifier: Modifier = Modifier) {
    Jetpack_Compose_LearningTheme {
        AnimationSizeDemo()
    }
}