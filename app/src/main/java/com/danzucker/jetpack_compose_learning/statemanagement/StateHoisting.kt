package com.danzucker.jetpack_compose_learning.statemanagement

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.danzucker.jetpack_compose_learning.ui.theme.Jetpack_Compose_LearningTheme

@Composable
fun StatefulCounter(modifier: Modifier = Modifier) {
    var count by rememberSaveable { mutableIntStateOf(0) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Button(onClick = {
            count++
        }) {
            Text(text = "Click me: $count")
        }
    }
}

@Composable
fun StateLessCounter(
    counter: Int,
    onCounterChanged: () -> Unit,
    modifier: Modifier = Modifier
) {

    Box(
        modifier = modifier
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Button(onClick = onCounterChanged) {
            Text(text = "Click me: $counter")
        }
    }
}



@Composable
fun StateHoistingDemo(
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        var counter by rememberSaveable { mutableIntStateOf(0) }
        StateLessCounter(
            counter = counter,
            onCounterChanged = {
                counter++
            }
        )

        Button(onClick = {
            counter = 0
        }) {
            Text(text = "Reset Counter")
        }
    }

}

@Preview(
    showBackground = true
)
@Composable
private fun StateHoistingDemoPreview() {
    Jetpack_Compose_LearningTheme {
        StateHoistingDemo()
    }
}

@Preview
@Composable
private fun StateHoistingPreview() {
    Jetpack_Compose_LearningTheme {
        StatefulCounter()
    }
}