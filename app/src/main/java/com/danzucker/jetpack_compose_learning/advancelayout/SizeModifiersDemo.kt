package com.danzucker.jetpack_compose_learning.advancelayout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.danzucker.jetpack_compose_learning.advancelayout.utils.printConstraints
import com.danzucker.jetpack_compose_learning.ui.theme.Jetpack_Compose_LearningTheme

@Composable
fun SizeModifiersDemo(modifier: Modifier = Modifier) {
    Row(
        modifier = Modifier
            .height(100.dp)
            .fillMaxWidth()
            .background(Color.Red)
    ) {

        Box(
            modifier = Modifier
                .height(100.dp)
                .printConstraints("Before 1. fillMaxWidth")
                .requiredWidth(300.dp)
                .wrapContentWidth(
                    align = Alignment.End
                )
                .printConstraints("After 1. fillMaxWidth")
                .background(Color.Yellow),
            contentAlignment = Alignment.CenterEnd
        ) {
            Text("This text is for demo purposes")
        }

        Box(
            modifier = Modifier
                .height(100.dp)
                .printConstraints("Before 2. fillMaxWidth")
                .requiredWidth(300.dp)
                .wrapContentWidth()
                .printConstraints("After 2. fillMaxWidth")
                .background(Color.Green),
            contentAlignment = Alignment.CenterEnd
        ) {
            Text("This text is for demo purposes")
        }
    }
}

@Preview
@Composable
private fun SizeModifiersDemoPreview() {
    Jetpack_Compose_LearningTheme {
        SizeModifiersDemo()
    }
}