package com.danzucker.jetpack_compose_learning.performanceoptimization

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * We can use layout inspector and our debugger to spot performance issues in our code.
 */

@Composable
fun MyScreen(modifier: Modifier = Modifier) {
    var counter by remember {
        mutableIntStateOf(0)
    }

    Column(
        modifier = modifier
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        MyCounter(
            counter = counter,
            onIncrement = { counter++ },
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(
            text = "Sample text",
            fontSize = 20.sp,
            color = Color.White
        )
    }
}

@Composable
fun MyCounter(
    counter: Int,
    onIncrement: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onIncrement,
        modifier = modifier
    ) {
        Text("I've been clicked $counter times")
    }
}