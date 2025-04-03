package com.danzucker.jetpack_compose_learning.launcheffect

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import kotlinx.coroutines.delay


@Composable
fun ProduceStateDemo(
    modifier: Modifier = Modifier
) {
    val counter by produceState(0) {
        while (true) {
            delay(1000)
            value += 1
        }
    }

    Text(
        text = counter.toString(),
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize()
    )
}