package com.danzucker.jetpack_compose_learning

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.safeGesturesPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.danzucker.jetpack_compose_learning.advancelayout.LazyColumnDemo
import com.danzucker.jetpack_compose_learning.advancelayout.SizeModifiersDemo
import com.danzucker.jetpack_compose_learning.advancelayout.SizePositionModifier
import com.danzucker.jetpack_compose_learning.advancelayout.utils.SubComposePagedRow
import com.danzucker.jetpack_compose_learning.basiclayout.HotelManagementScreen
import com.danzucker.jetpack_compose_learning.modifier.FocusManagementModifier
import com.danzucker.jetpack_compose_learning.modifier.SpacingModifierDemo
import com.danzucker.jetpack_compose_learning.statemanagement.assignment.TodoScreenRoot
import com.danzucker.jetpack_compose_learning.statemanagement.numberguess.NumberGuessScreenRoot
import com.danzucker.jetpack_compose_learning.ui.theme.Jetpack_Compose_LearningTheme
import kotlin.random.Random

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
                    LazyColumnDemo(
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