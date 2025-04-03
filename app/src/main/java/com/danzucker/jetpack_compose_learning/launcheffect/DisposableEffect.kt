package com.danzucker.jetpack_compose_learning.launcheffect

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.danzucker.jetpack_compose_learning.ui.theme.Jetpack_Compose_LearningTheme

@Composable
fun DisposableEffectDemo(
    modifier: Modifier = Modifier
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(
        lifecycleOwner.lifecycle
    ) {

        val observer = LifecycleEventObserver { _, event ->
            when(event) {
                androidx.lifecycle.Lifecycle.Event.ON_START -> {
                    println("OnStart was called")
                }
                androidx.lifecycle.Lifecycle.Event.ON_STOP -> {
                    println("OnStop was called")
                }
                else -> {}
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            println("OnDispose was called")
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
}

@Preview
@Composable
private fun DisposableEffectPreview() {
    Jetpack_Compose_LearningTheme {
        DisposableEffectDemo()
    }
}