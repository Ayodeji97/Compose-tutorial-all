package com.danzucker.jetpack_compose_learning.advancelayout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.danzucker.jetpack_compose_learning.ui.theme.Jetpack_Compose_LearningTheme


@Composable
fun IntrinsicSizeDemo(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .width(intrinsicSize = IntrinsicSize.Max)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = "Option 1, this is now a much longer text"
            )
            Checkbox(
                checked = true,
                onCheckedChange = {}
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = "Option 1, but in longer form"
            )
            Checkbox(
                checked = false,
                onCheckedChange = {}
            )
        }
    }
}

@Composable
fun AnotherIntrinsicSizeDemo(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .width(intrinsicSize = IntrinsicSize.Min)
    ) {
        Text(
            text = "Hello world, I am a much longer text at the moment",
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}



@Preview
@Composable
private fun IntrinsicSizeDemoPreview() {
    Jetpack_Compose_LearningTheme {
       // IntrinsicSizeDemo()
        AnotherIntrinsicSizeDemo()
    }
}