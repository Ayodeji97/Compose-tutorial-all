package com.danzucker.jetpack_compose_learning.compositionlocal

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.danzucker.jetpack_compose_learning.modifier.ClickableModifiersDemo
import com.danzucker.jetpack_compose_learning.ui.theme.Jetpack_Compose_LearningTheme
import kotlinx.coroutines.launch

val LocalSnackbarState = compositionLocalOf {
    SnackbarHostState()
}

@Composable
fun CompositionLocalAssignment(
    modifier: Modifier = Modifier
) {
    val state = LocalSnackbarState.current
    val scope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = {
            SnackbarHost(state)
        }
    ) { innerPadding ->
        Button(
            onClick = {
                scope.launch {
                    state.showSnackbar(
                        message = "Hello World"
                    )
                }
            },
            modifier = Modifier.padding(innerPadding)
        ) {
            Text("Show Snackbar")
        }
    }
}

@Preview
@Composable
private fun CompositionLocalAssignmentPreview() {
    Jetpack_Compose_LearningTheme {
        CompositionLocalAssignment()
    }
}