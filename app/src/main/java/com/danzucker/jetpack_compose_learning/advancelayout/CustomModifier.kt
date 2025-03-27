package com.danzucker.jetpack_compose_learning.advancelayout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.danzucker.jetpack_compose_learning.ui.theme.Jetpack_Compose_LearningTheme

inline fun Modifier.applyIf(
    condition: Boolean,
    modifier: Modifier.() -> Modifier
): Modifier = if (condition) {
    this.then(modifier())
} else {
    this
}

@Composable
fun SimpleModifierDemo(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .size(100.dp)
            .applyIf(false) {
                background(Color.Yellow)
            }
    )
}

@Preview
@Composable
private fun SimpleModifierDemoPreview() {
    Jetpack_Compose_LearningTheme {
        SimpleModifierDemo()
    }
}
