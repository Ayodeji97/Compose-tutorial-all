package com.danzucker.jetpack_compose_learning.animation

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.danzucker.jetpack_compose_learning.ui.theme.Jetpack_Compose_LearningTheme
import kotlinx.coroutines.launch
import java.util.Locale

@Composable
fun ValueAnimationAssignment(
    value: Float,
    maxValue: Float,
    unit: String,
    color: Color = Color.Red,
    modifier: Modifier = Modifier,
    strokeWidth: Dp = 10.dp
) {

    var currentValue by remember { mutableFloatStateOf(value) }

    val animatedProgress = remember {
        Animatable(
            initialValue = 0f // Or the value if you want to start from a specific value
        )
    }


    //I might want to use LaunchEffect here to animate to the [value] pass from outside on screen launch
    /**
     LaunchedEffect(value) {
        animatedProgress.animateTo(
            targetValue = value,
            animationSpec = tween(durationMillis = 3000)
        )
    }
    **/

    val sweepAngle by remember {
        derivedStateOf { (animatedProgress.value / maxValue).coerceIn(0f, 1f) * 360f }
    }


    val scope = rememberCoroutineScope()

    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            space = 10.dp,
            alignment = Alignment.CenterVertically
        )
    ) {

        CircleProgress(
            animatedProgress = animatedProgress,
            sweepAngle = sweepAngle,
            unit = unit,
            color = color,
            modifier = Modifier
                .size(200.dp)
                .padding(strokeWidth / 2),
            strokeWidth = strokeWidth
        )

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(
                space = 10.dp,
                alignment = Alignment.CenterHorizontally
            )

        ) {
            listOf(0.1f, 0.5f, 0.9f).forEach { percentage ->
                ValueButton(
                    value = maxValue * percentage,
                    unit = unit,
                    onClick = {
                        currentValue = maxValue * percentage
                        scope.launch {
                            animatedProgress.animateTo(
                                targetValue = currentValue,
                                animationSpec = tween(
                                    durationMillis = 3000
                                )
                            )
                        }
                    },
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}


@Composable
fun CircleProgress(
    animatedProgress: Animatable<Float, AnimationVector1D>,
    sweepAngle: Float,
    unit: String,
    color: Color = Color.Red,
    modifier: Modifier = Modifier,
    strokeWidth: Dp
) {

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = Modifier
                .size(150.dp)

        ) {
            val stroke = Stroke(width = strokeWidth.toPx(), cap = StrokeCap.Round)
            drawArc(
                color = color,
                startAngle = -90f,
                sweepAngle = sweepAngle,
                useCenter = false,
                style = stroke
            )
        }

        Text(
            text = String.format(
                Locale.getDefault(),
                "%.1f%s",
                animatedProgress.value,
                unit
            ),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }
}

@Composable
fun ValueButton(
    value: Float,
    unit: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier
    ) {
        Text(
            text = "${value.toInt()} $unit",
            color = Color.White
        )
    }
}

@Preview
@Composable
private fun ValueAnimationAssignmentUpdatedPreview() {
    Jetpack_Compose_LearningTheme {
        ValueAnimationAssignment(
            value = 20f,
            maxValue = 100f,
            unit = "%",
            color = Color.Red,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        )
    }
}