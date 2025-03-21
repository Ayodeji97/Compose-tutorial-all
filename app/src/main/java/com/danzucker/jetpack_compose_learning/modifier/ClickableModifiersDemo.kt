package com.danzucker.jetpack_compose_learning.modifier

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.danzucker.jetpack_compose_learning.ui.theme.Jetpack_Compose_LearningTheme

@Composable
fun ClickableModifiersDemo(
    modifier: Modifier = Modifier
) {

    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    Box(
        modifier = modifier
            .size(100.dp)
            .background(
                if (isPressed) Color.Red else Color.Black
            )
            .clickable(
                interactionSource,
                LocalIndication.current
            ) {
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Here is my text",
            color = Color.White
        )
    }
}

@Preview
@Composable
private fun ClickableModifiersDemoPreview() {
    Jetpack_Compose_LearningTheme {
        ClickableModifiersDemo()
    }
}