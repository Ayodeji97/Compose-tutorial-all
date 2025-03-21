package com.danzucker.jetpack_compose_learning.modifier

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.danzucker.jetpack_compose_learning.ui.theme.Jetpack_Compose_LearningTheme

// We will learn more about offset modifier in this example
@Composable
fun OffsetModifier(
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .size(100.dp)
            .background(Color.Red)

    ) {

        Text(
            text = "Offset Modifier",
            modifier= Modifier
                .offset(
                    x = 50.dp,
                    y = 20.dp
                )
                .background(Color.Green)
        )

        Text(
            text = "Offset Modifier",
            modifier= Modifier
                .background(Color.Green)
        )
    }
}

@Preview
@Composable
private fun OffsetModifierPreview() {
    Jetpack_Compose_LearningTheme {
        OffsetModifier()
    }
}