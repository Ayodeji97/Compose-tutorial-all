package com.danzucker.jetpack_compose_learning.launcheffect

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.danzucker.jetpack_compose_learning.ui.theme.Jetpack_Compose_LearningTheme
import kotlinx.coroutines.launch

@Composable
fun LaunchEffectDemo(
    modifier: Modifier = Modifier
) {
    var counter by remember {
        mutableIntStateOf(0)
    }

    val snackbarHostState = remember {
        SnackbarHostState()
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) {
        if (it) {
            println("Permission granted")
        } else {
            println("Permission denied")
        }
    }

    LaunchedEffect(true) {
        launcher.launch(android.Manifest.permission.ACCESS_COARSE_LOCATION)
    }

    LaunchedEffect(key1 = counter) {
        if (counter % 2 == 0) {
            snackbarHostState.showSnackbar(
                message = "Counter: $counter"
            )
        }
    }

    /**
     * Don't launch a coroutine directly in a composable function. This is a side effect
     * If you really need to launch a courotine using the rememberCoroutineScope (which is rarely needed,
     * do it in a lambda
     *
     * Another way is the LaunchEffect
     */
    val scope = rememberCoroutineScope()

    Scaffold(
        modifier = modifier
            .fillMaxSize(),
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState
            )
        }
    ) { innerPadding ->
        Button(
            onClick = {
                counter++
//                if (counter % 2 == 0) {
//                    scope.launch {
//                        snackbarHostState.showSnackbar(
//                            message = "Counter: $counter"
//                        )
//                    }
//                }
            },
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .wrapContentSize()
        ) {
            Text("Counter: $counter")
        }
    }
}

@Preview
@Composable
private fun LaunchEffectDemoPreview() {
    Jetpack_Compose_LearningTheme {
        LaunchEffectDemo()
    }
}