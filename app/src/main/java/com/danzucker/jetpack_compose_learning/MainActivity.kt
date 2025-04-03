package com.danzucker.jetpack_compose_learning

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.danzucker.jetpack_compose_learning.advancelayout.CounterComposable
import com.danzucker.jetpack_compose_learning.advancelayout.LazyMindMap
import com.danzucker.jetpack_compose_learning.advancelayout.LazyMindMapExtension
import com.danzucker.jetpack_compose_learning.advancelayout.MindMapItem
import com.danzucker.jetpack_compose_learning.advancelayout.MindMapItemExtension
import com.danzucker.jetpack_compose_learning.advancelayout.OverflowLayout
import com.danzucker.jetpack_compose_learning.advancelayout.SimpleTodoItem
import com.danzucker.jetpack_compose_learning.personalplayground.ModifierPlayGround
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
                    val mindMindItems = remember {
                        listOf(
                            MindMapItemExtension(
                                absoluteOffset = Offset(
                                    x = 0f,
                                    y = 0f
                                ),
                                constraints = Constraints(
                                    minWidth = 100,
                                    maxWidth = 1000,
                                    minHeight = 100,
                                    maxHeight = 400
                                ),
                                content = {
                                    SimpleTodoItem()
                                }
                            ),

                            MindMapItemExtension(
                                absoluteOffset = Offset(
                                    x = 1000f,
                                    y = 1500f
                                ),
                                constraints = Constraints(
                                    minWidth = 600,
                                    maxWidth = 1000,
                                    minHeight = 150,
                                    maxHeight = 800
                                ),
                                content = {
                                    CounterComposable()
                                }
                            ),
                        )
                    }

                    var mindMapOffset by remember {
                        mutableStateOf(IntOffset.Zero)
                    }

                    LazyMindMapExtension(
                        items = mindMindItems,
                        mindMapOffset = mindMapOffset,
                        onDrag = { delta ->
                            mindMapOffset += delta
                        },
                        itemModifier = Modifier
                            .border(
                                width = 2.dp,
                                color = Color.LightGray
                            ),
                        modifier = Modifier
                            .fillMaxSize()
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