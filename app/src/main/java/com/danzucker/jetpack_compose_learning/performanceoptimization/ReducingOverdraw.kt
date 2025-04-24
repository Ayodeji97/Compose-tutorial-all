package com.danzucker.jetpack_compose_learning.performanceoptimization

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 *
 * Not really needed, just in rare cases
 */
@Composable
fun OverdrawDemo(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .background(Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            Box(
                modifier = Modifier
                    .height(100.dp)
                    .width(150.dp)
                    .background(Color.Green)
            )
            //            Box(
            //                modifier = Modifier
            //                    .size(100.dp)
            //                    .offset(x = -(50.dp))
            //                    .background(Color.Green)
            //            )
        }
    }
}