package com.danzucker.jetpack_compose_learning.modifier

import androidx.compose.foundation.border
import androidx.compose.foundation.focusGroup
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.danzucker.jetpack_compose_learning.ui.theme.Jetpack_Compose_LearningTheme

@Composable
fun FocusManagementModifier(modifier: Modifier = Modifier) {

    val focusRequester = remember {
        FocusRequester()
    }

    val focusManager = LocalFocusManager.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        TextField(
            value = "",
            onValueChange = {},
            modifier = Modifier
                .focusRequester(focusRequester),
            keyboardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(
                        FocusDirection.Down
                    )
                }
            ),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            )
        )

        // What if we want to move focus to a composable that by default does not accept focus?

        var isFocus by remember { mutableStateOf(false) }

        var hasFocus by remember { mutableStateOf(false) }
        /**
         *  Box(
         *             modifier = Modifier
         *                 .fillMaxWidth()
         *                 .height(100.dp)
         *                 .border(
         *                     width = 5.dp,
         *                     color = if (isFocus) Color.Green else Color.Gray
         *                 )
         *                 .onFocusChanged {
         *                     isFocus = it.isFocused
         *                 }
         *                 .focusable()
         *         )
         */

        // If we want to move focus to child composable that the parent does not have focus, we can use the FocusGroup composable.
        // We can use focusGroup() modifier to make the parent composable focusable.
        // And hasFocus to check if the parent composable has focus.
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 5.dp,
                    color = if (hasFocus) Color.Green else Color.Gray
                )
                .onFocusChanged {
                    hasFocus = it.hasFocus
                }
                .focusGroup(),
            verticalArrangement = spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            TextField(
                value = "",
                onValueChange = {},
                keyboardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(
                            FocusDirection.Down
                        )
                    }
                ),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                )
            )

            TextField(
                value = "",
                onValueChange = {},
                keyboardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(
                            FocusDirection.Down
                        )
                    }
                ),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                )
            )
        }

        TextField(
            value = "",
            onValueChange = {},
            keyboardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(
                        FocusDirection.Down
                    )
                }
            ),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            )
        )

        TextField(
            value = "",
            onValueChange = {},
        )

        Button(
            onClick = {
                focusRequester.requestFocus()
            }
        ) {
            Text(text = "Start filling the text fields")
        }

        // We can clear focus using the FocusManager
        Button(
            onClick = {
                focusManager.clearFocus()
            }
        ) {
            Text(text = "Clear focus")
        }
    }
}

@Preview
@Composable
private fun FocusManagementModifierPreview() {
    Jetpack_Compose_LearningTheme {
        FocusManagementModifier()
    }
}