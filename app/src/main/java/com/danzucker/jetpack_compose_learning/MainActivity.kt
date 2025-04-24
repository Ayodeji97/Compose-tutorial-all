@file:OptIn(ExperimentalComposeUiApi::class)

package com.danzucker.jetpack_compose_learning

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.tooling.preview.Preview
import com.danzucker.jetpack_compose_learning.animation.LookaheadLayoutAnimation
import com.danzucker.jetpack_compose_learning.launcheffect.DisposableEffectDemo
import com.danzucker.jetpack_compose_learning.launcheffect.LaunchEffectAssignment
import com.danzucker.jetpack_compose_learning.launcheffect.LaunchEffectDemo
import com.danzucker.jetpack_compose_learning.performanceoptimization.DeferredStateReads
import com.danzucker.jetpack_compose_learning.performanceoptimization.KeysCustomLayout
import com.danzucker.jetpack_compose_learning.performanceoptimization.LazyListPerformance
import com.danzucker.jetpack_compose_learning.performanceoptimization.MyScreen
import com.danzucker.jetpack_compose_learning.performanceoptimization.OverdrawDemo
import com.danzucker.jetpack_compose_learning.performanceoptimization.mainsafety.BitmapCompressor
import com.danzucker.jetpack_compose_learning.performanceoptimization.mainsafety.PhotoPickerScreen
import com.danzucker.jetpack_compose_learning.ui.theme.Jetpack_Compose_LearningTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Jetpack_Compose_LearningTheme {

                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .semantics {
                            testTagsAsResourceId = true
                        }
                ) { innerPadding ->

                    LazyListPerformance(
                        modifier = Modifier
                            .padding(innerPadding)
                    )

                }
            }
        }
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Jetpack_Compose_LearningTheme {
        Greeting("Android")
    }
}