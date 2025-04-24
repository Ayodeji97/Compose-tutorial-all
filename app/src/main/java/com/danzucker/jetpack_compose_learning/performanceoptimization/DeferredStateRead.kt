package com.danzucker.jetpack_compose_learning.performanceoptimization

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.coerceIn
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import com.danzucker.jetpack_compose_learning.ui.theme.Jetpack_Compose_LearningTheme
import kotlinx.coroutines.launch

/**
 * Defferring State read is a technique used to optimize the performance of a Composable function
 * It allows you to read the state of a Composable function only when it is needed, rather than
 * reading them every time the Composable function is recomposed.
 *
 * Deffering can be achieve by using a lambda variant of the state read.
 *
 * Whenever we have a state that is expensive to read, we can use the lambda variant of the state read
 */
@Composable
fun DeferredStateReads(modifier: Modifier = Modifier) {
    val state = rememberLazyListState()
    val scope = rememberCoroutineScope()
    val fabOffset by remember {
        derivedStateOf {
            val percentage = (1f - (state.firstVisibleItemIndex / 10f))
            (percentage * 100.dp).coerceIn(
                minimumValue = 0.dp,
                maximumValue = 100.dp
            )
        }
    }

    // We can demonstrate this again using color.
    val infiniteTransition = rememberInfiniteTransition(label = "infiniteTransition")
    val color by infiniteTransition.animateColor(
        initialValue = Color.Red,
        targetValue = Color.Blue,
        label = "colorAnimation",
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 3000,
                delayMillis = 0,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Reverse
        )
    )
    Scaffold(
        modifier = modifier,
        floatingActionButton = {
            MovingFloatingActionButton(
               // color = { color },
                offset = { fabOffset },
                onClick = {
                    scope.launch {
                        state.animateScrollToItem(0)
                    }
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            state = state,
            modifier = modifier
                .fillMaxSize(),
            contentPadding = innerPadding
        ) {
            items(100) {
                Text(
                    text = "Item $it",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            }
        }
    }
}

@Composable
fun MovingFloatingActionButton(
   // color: () -> Color,
    offset: () -> Dp,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val density = LocalDensity.current
    FloatingActionButton(
        onClick = onClick,
        modifier = modifier
//            .drawBehind {
//                drawRect(
//                    color = color()
//                )
//            }
            .offset {
                /**
                 * This offset lambda is only called in the layout phase of the composable and not on
                 * the composition phase.
                 */
                IntOffset(
                    x = 0,
                    y = with(density) { offset().roundToPx() }
                )
            },
    ) {
        Icon(
            imageVector = Icons.Default.KeyboardArrowUp,
            contentDescription = "Scroll to top"
        )
    }
}

@Preview
@Composable
private fun DeferredStateReadsPreview() {
    Jetpack_Compose_LearningTheme {
        DeferredStateReads(
            modifier = Modifier
                .fillMaxSize()
        )
    }
}