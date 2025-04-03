package com.danzucker.jetpack_compose_learning.launcheffect

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
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
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.danzucker.jetpack_compose_learning.ui.theme.Jetpack_Compose_LearningTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MyEffectHandlerviewModel : ViewModel() {
    private val _counter = MutableStateFlow(0)
    val counter = _counter.asStateFlow()

    val snackbarHostState = SnackbarHostState()

    val lazyListState = LazyListState()
    val canScrollToTop = snapshotFlow {
        lazyListState.firstVisibleItemIndex
    }
        .map {
            it >= 10
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            false
        )

    init {

        viewModelScope.launch {
            counter.collectLatest { lastestValue ->
                if (lastestValue % 2 == 0) {
                    snackbarHostState.showSnackbar(
                        message = "Counter: $lastestValue"
                    )
                }

            }
        }
    }

    fun incrrementCounter() {
        _counter.value++
    }

    fun scrollToTop(uiScope: CoroutineScope) {
        uiScope.launch {
            lazyListState.animateScrollToItem(0)
        }
    }
}

@Composable
fun LaunchEffectDemo(
    modifier: Modifier = Modifier
) {
    val viewModel = viewModel<MyEffectHandlerviewModel>()
    val counter by viewModel.counter.collectAsStateWithLifecycle()
    val canScrollToTop by viewModel.canScrollToTop.collectAsStateWithLifecycle()

    var counter1 by remember {
        mutableIntStateOf(0)
    }

    val snackbarHostState1 = remember {
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
//
    // you don't need launch event
//    LaunchedEffect(key1 = counter) {
//        if (counter % 2 == 0) {
//            snackbarHostState.showSnackbar(
//                message = "Counter: $counter"
//            )
//        }
//    }

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
                hostState = viewModel.snackbarHostState
            )
        },
        floatingActionButton = {
            if (canScrollToTop) {
                FloatingActionButton(onClick = {
                    viewModel.scrollToTop(uiScope = scope)
                }) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowUp,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            LazyColumn(
                state = viewModel.lazyListState,
                modifier = Modifier.weight(1f)
            ) {
                items(100) {
                    Text(
                        text = "Item $it",
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxSize()
                    )
                }
            }

            Button(
                onClick = {
                    // counter++
                    viewModel.incrrementCounter()
//                if (counter % 2 == 0) {
//                    scope.launch {
//                        snackbarHostState.showSnackbar(
//                            message = "Counter: $counter"
//                        )
//                    }
//                }
                },
                modifier = Modifier
                    .fillMaxWidth()

            ) {
                Text("Counter: $counter")
            }
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