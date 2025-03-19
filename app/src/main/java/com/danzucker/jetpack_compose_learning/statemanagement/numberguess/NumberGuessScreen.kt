package com.danzucker.jetpack_compose_learning.statemanagement.numberguess

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.danzucker.jetpack_compose_learning.ui.theme.Jetpack_Compose_LearningTheme

@Composable
fun NumberGuessScreenRoot(modifier: Modifier = Modifier) {
    val viewModel = viewModel<NumberGuessViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()

    NumberGuessScreen(
        modifier = modifier
            .background(Color.White),
        onAction = viewModel::onAction,
        state = state,
    )
}


@Composable
fun NumberGuessScreen(
    state: NumberGuessState,
    onAction: (NumberGuessAction) -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

       Text(
           text = "Guess the number between 1 and 100",
           fontSize = 18.sp,
           color = Color.Black
       )

        TextField(
            value = state.numberText,
            onValueChange = { newText ->
                onAction(NumberGuessAction.OnNumberTextChange(newText))
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            label = {
                Text(text = "Enter a number")
            },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.Gray,
                focusedContainerColor = Color.Gray
            )

        )

        Button(onClick = {
            onAction(NumberGuessAction.OnGuessButtonClick)
        }) {
            Text(text = "Make Guess")
        }


        if (state.guessText != null) {
            Text(
                text = state.guessText,
                fontSize = 18.sp,
                color = Color.Black
            )
        }

        if (state.isGuessCorrect) {
            Button(onClick = {
                onAction(NumberGuessAction.OnResetButtonClick)
            }) {
                Text(text = "Reset game")
            }
        }
    }
}


@Preview(
    showBackground = true
)
@Composable
private fun NumberGuessScreenPreview() {
    Jetpack_Compose_LearningTheme {
        NumberGuessScreen(
            state = NumberGuessState(),
            onAction = {}
        )
    }
}