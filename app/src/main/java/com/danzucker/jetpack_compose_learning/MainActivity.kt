package com.danzucker.jetpack_compose_learning

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.danzucker.jetpack_compose_learning.advancelayout.OverflowLayout
import com.danzucker.jetpack_compose_learning.personalplayground.ModifierPlayGround
import com.danzucker.jetpack_compose_learning.ui.theme.Jetpack_Compose_LearningTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Jetpack_Compose_LearningTheme {
                var isOverFlowing by remember { mutableStateOf(false) }

                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                ) { innerPadding ->
                    OverflowLayout(
                        isOverflowing = isOverFlowing,
                        modifier = Modifier
                            .padding(innerPadding),
                        mainContent = {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color.White)
                                    .padding(16.dp)
                            ) {
                                Text(
                                    text = "This is a toggle slection",
                                    color = Color.Black
                                )

                                IconButton(onClick = {
                                    isOverFlowing = !isOverFlowing
                                }) {
                                    Icon(
                                        imageVector = if (isOverFlowing) {
                                            Icons.Default.KeyboardArrowUp
                                        } else Icons.Default.KeyboardArrowDown,
                                        contentDescription = null,
                                        tint = Color.Black
                                    )
                                }
                            }
                        },
                        overflowContent = {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color.Yellow)
                                    .padding(16.dp)
                            ) {
                                Text(
                                    text = "Secret section",
                                    color = Color.Black
                                )
                            }
                        }
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