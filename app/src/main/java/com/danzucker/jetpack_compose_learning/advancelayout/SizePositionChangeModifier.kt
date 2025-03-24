package com.danzucker.jetpack_compose_learning.advancelayout

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.danzucker.jetpack_compose_learning.ui.theme.Jetpack_Compose_LearningTheme

@Composable
fun SizePositionModifier(modifier: Modifier = Modifier) {
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .onSizeChanged { newSize ->
                size = newSize
            }
            .onGloballyPositioned { layoutCoordinates ->
                println("Position: ${layoutCoordinates.positionInWindow()}")
                layoutCoordinates.size
            }
    ) {
        Text(
            text = if (size.width >= 1500) {
                "Very wide screen"
            } else "Normal screen"
        )
    }


}

@Preview
@Composable
private fun SizePositionModifierPreview() {
    Jetpack_Compose_LearningTheme {
        SizePositionModifier()
    }
}