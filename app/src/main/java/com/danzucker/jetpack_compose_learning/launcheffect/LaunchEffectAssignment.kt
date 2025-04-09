package com.danzucker.jetpack_compose_learning.launcheffect

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.danzucker.jetpack_compose_learning.ui.theme.Jetpack_Compose_LearningTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


class LaunchEffectViewModel : ViewModel() {

    val snackbarHostState = SnackbarHostState()
    val lazyListState = LazyListState()

    init {

        viewModelScope.launch {
            snapshotFlow {
                with(lazyListState.layoutInfo) {
                    if (totalItemsCount == 0) {
                        true
                    } else visibleItemsInfo.lastOrNull()?.index == totalItemsCount - 1
                }
            }
                .distinctUntilChanged()
                .collectLatest {
                    if (it) {
                        snackbarHostState.showSnackbar(
                            message = "Scrolled to bottom"
                        )
                    }
                }

        }



        /**
         * Or this
         */
        /**
        snapshotFlow {
            with(lazyListState.layoutInfo) {
                if (totalItemsCount == 0) {
                    true
                } else visibleItemsInfo.lastOrNull()?.index == totalItemsCount - 1
            }
        }
            .distinctUntilChanged()
            .onEach {
                if (it) {
                    snackbarHostState.showSnackbar(
                        message = "Scrolled to bottom"
                    )
                }
            }
            .launchIn(viewModelScope)
        **/
    }
}


@Composable
fun LaunchEffectAssignment(
    items: List<String>,
    modifier: Modifier = Modifier
) {
    val viewModel = viewModel<LaunchEffectViewModel>()

    Scaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = viewModel.snackbarHostState
            )
        }
    ) { innerPadding ->

        LazyColumn(
            state = viewModel.lazyListState,
            modifier = Modifier
                .padding(innerPadding)
        ) {
            items(
                items.size
            ) { index ->
                LaunchEffectItem(
                    item = items[index],
                    modifier = modifier
                        .fillMaxWidth()
                )
            }
        }
    }

}

@Composable
fun LaunchEffectItem(
    item: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = item,
        modifier = modifier
    )
}


@Preview
@Composable
private fun LaunchEffectAssignmentPreview() {
    Jetpack_Compose_LearningTheme {
        LaunchEffectAssignment(
            items = listOf(
                "Item 1", "Item 2", "Item 3",
                "Item 4", "Item 5", "Item 6",
                "Item 7", "Item 8", "Item 9",
                "Item 10", "Item 11", "Item 12",
                "Item 13", "Item 14", "Item 15",
                "Item 16", "Item 17", "Item 18",
                "Item 19", "Item 20", "Item 21",
                "Item 22", "Item 23", "Item 24",
                "Item 25", "Item 26", "Item 27",
                "Item 28", "Item 29", "Item 30",
                "Item 31", "Item 32", "Item 33",
                "Item 34", "Item 35", "Item 36",
                "Item 37", "Item 38", "Item 39",
                "Item 40", "Item 41", "Item 42",
                "Item 43", "Item 44", "Item 45",
            )
        )
    }
}