package com.danzucker.jetpack_compose_learning.basiclayout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import androidx.window.core.layout.WindowWidthSizeClass
import com.danzucker.jetpack_compose_learning.ui.theme.Jetpack_Compose_LearningTheme

@Composable
fun WindowSizeClassDemo(
    modifier: Modifier = Modifier
) {
    val windowClass = currentWindowAdaptiveInfo().windowSizeClass

    Scaffold(
        modifier = modifier
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when (windowClass.windowWidthSizeClass) {
                WindowWidthSizeClass.COMPACT -> {
                    // Handle compact width
                   // Text("Compact Width", modifier = modifier)
                    ListComposeUi(modifier = modifier)
                }

                WindowWidthSizeClass.MEDIUM -> {
                    // Handle medium width
                    //Text("Medium Width", modifier = modifier)
                    ListComposeUi(modifier = modifier)
                }

                WindowWidthSizeClass.EXPANDED -> {
                    // Handle large width
                   // Text("Expanded Width", modifier = modifier)
                    // Simulating have a side panel
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {

                        Column(
                            modifier =
                            Modifier
                                .fillMaxHeight()
                                .weight(3f)
                                .background(Color.Blue)
                        ) {
                            Text("Side Panel", modifier = modifier)
                            Text("Side Panel", modifier = modifier)
                            Text("Side Panel", modifier = modifier)
                        }

                        ListComposeUi(
                            modifier = Modifier
                                .fillMaxHeight()
                                .weight(7f)
                        )
                    }
                }
            }

        }

    }
}

@Composable
fun ListComposeUi(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {
        items(100) { index ->
            Text(
                text = "Item $index",
                modifier = Modifier
                    .padding(16.dp)
            )
        }
    }
}

//@PreviewScreenSizes
@Preview(
    showBackground = true,
    device = Devices.NEXUS_10
)
@Composable
private fun WindowSizeClassDemoPreview() {
    Jetpack_Compose_LearningTheme {
        WindowSizeClassDemo()
    }
}