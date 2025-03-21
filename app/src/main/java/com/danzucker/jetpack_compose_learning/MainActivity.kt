package com.danzucker.jetpack_compose_learning

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.danzucker.jetpack_compose_learning.basiclayout.HotelManagementScreen
import com.danzucker.jetpack_compose_learning.modifier.FocusManagementModifier
import com.danzucker.jetpack_compose_learning.statemanagement.assignment.TodoScreenRoot
import com.danzucker.jetpack_compose_learning.statemanagement.numberguess.NumberGuessScreenRoot
import com.danzucker.jetpack_compose_learning.ui.theme.Jetpack_Compose_LearningTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Jetpack_Compose_LearningTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                   HotelManagementScreen(
//                       modifier = Modifier
//                           .padding(innerPadding)
//                   )

//                    NumberGuessScreenRoot(
//                        modifier = Modifier
//                            .padding(innerPadding)
//                    )

//                    TodoScreenRoot(
//                        modifier = Modifier
//                            .padding(innerPadding)
//                    )

                    FocusManagementModifier(
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