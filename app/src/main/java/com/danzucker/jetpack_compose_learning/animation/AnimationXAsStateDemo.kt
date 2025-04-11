package com.danzucker.jetpack_compose_learning.animation

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.danzucker.jetpack_compose_learning.ui.theme.Jetpack_Compose_LearningTheme

@Composable
fun AnimationXAsStateDemo(modifier: Modifier = Modifier) {

    var toggle by remember { mutableStateOf(false) }

    val ratio by animateFloatAsState(
        targetValue = if (toggle) 1f else 0f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow,
        ),
        label = "ration animation"
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .graphicsLayer {
                        rotationZ = 405f * ratio
                        scaleX = 1 - (ratio * 0.5f)
                        scaleY = 1 - (ratio * 0.5f)
                    }
                    .size(100.dp)
                    .background(Color.Red)
            )
        }

        Button(
            onClick = {
                toggle = !toggle
            },
        ) {
            Text("Toggle")
        }
    }
}

@Preview
@Composable
private fun AnimationXAsStateDemoPreview() {
    Jetpack_Compose_LearningTheme {
        AnimationXAsStateDemo()
    }
}