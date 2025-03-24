package com.danzucker.jetpack_compose_learning.advancelayout

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.danzucker.jetpack_compose_learning.ui.theme.Jetpack_Compose_LearningTheme

@Composable
fun BoxConstraints(modifier: Modifier = Modifier) {
    BoxWithConstraints(
//        modifier = modifier
//            .size(200.dp)
    ) {
        if (constraints.hasFixedWidth) {
            Text("Has a fixed width")
        } else {
            Text("Has a dynamic width")
        }
    }
}

@Preview
@Composable
private fun BoxConstraintsPreview() {
    Jetpack_Compose_LearningTheme {
        BoxConstraints()
    }
}