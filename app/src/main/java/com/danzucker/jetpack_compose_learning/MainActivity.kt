package com.danzucker.jetpack_compose_learning

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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
                    LaunchEffectDemo()
//                    val mindMindItems = remember {
//                        listOf(
//                            MindMapItemExtension(
//                                absoluteOffset = Offset(
//                                    x = 0f,
//                                    y = 0f
//                                ),
//                                constraints = Constraints(
//                                    minWidth = 100,
//                                    maxWidth = 1000,
//                                    minHeight = 100,
//                                    maxHeight = 400
//                                ),
//                                content = {
//                                    SimpleTodoItem()
//                                }
//                            ),
//
//                            MindMapItemExtension(
//                                absoluteOffset = Offset(
//                                    x = 1000f,
//                                    y = 1500f
//                                ),
//                                constraints = Constraints(
//                                    minWidth = 600,
//                                    maxWidth = 1000,
//                                    minHeight = 150,
//                                    maxHeight = 800
//                                ),
//                                content = {
//                                    CounterComposable()
//                                }
//                            ),
//                        )
//                    }
//
//                    var mindMapOffset by remember {
//                        mutableStateOf(IntOffset.Zero)
//                    }
//
//                    LazyMindMapExtension(
//                        items = mindMindItems,
//                        mindMapOffset = mindMapOffset,
//                        onDrag = { delta ->
//                            mindMapOffset += delta
//                        },
//                        itemModifier = Modifier
//                            .border(
//                                width = 2.dp,
//                                color = Color.LightGray
//                            ),
//                        modifier = Modifier
//                            .fillMaxSize()
//                            .padding(innerPadding)
//                    )
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