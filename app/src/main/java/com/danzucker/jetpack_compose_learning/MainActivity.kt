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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.danzucker.jetpack_compose_learning.launcheffect.DisposableEffectDemo
import com.danzucker.jetpack_compose_learning.launcheffect.LaunchEffectAssignment
import com.danzucker.jetpack_compose_learning.launcheffect.LaunchEffectDemo
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
                ) { innerPadding ->
                    //LaunchEffectDemo()
                    LaunchEffectAssignment(
                        items = listOf(
                            "Item 1", "Item 2", "Item 3",
                            "Item 4", "Item 5", "Item 6",
                            "Item 7", "Item 8", "Item 9",
                            "Item 10", "Item 11", "Item 12",
                            "Item 13", "Item 14", "Item 15",
                            "Item 16", "Item 17", "Item 18",
                            "Item 19", "Item 20", "Item 21",
                            "Item 22", "Item 23", "Item 24",
                            "Item 25", "Item 26", "Item 27",
                            "Item 28", "Item 29", "Item 30",
                            "Item 31", "Item 32", "Item 33",
                            "Item 34", "Item 35", "Item 36",
                            "Item 37", "Item 38", "Item 39",
                            "Item 40", "Item 41", "Item 42",
                            "Item 43", "Item 44", "Item 45",
                        )
                    )

//                    var toggle by remember {
//                        mutableStateOf(false)
//                    }
//                    if (!toggle) {
//                        DisposableEffectDemo()
//                    }
//
//                    Button(onClick = {
//                        toggle = !toggle
//                    }, modifier = Modifier
//                        .padding(innerPadding)
//                        .fillMaxSize()
//                        .wrapContentSize()
//
//                    ) {
//                        Text(text = "Toggle")
//                    }
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