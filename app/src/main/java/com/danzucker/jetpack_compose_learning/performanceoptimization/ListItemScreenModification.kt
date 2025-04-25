package com.danzucker.jetpack_compose_learning.performanceoptimization

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


private const val FILE_NAME = "simple_cat_image.jpg"

val initialItems = (1..100).map {
    ListItem(
        id = it,
        title = "List item $it",
        description = "Description $it",
        photo = null
    )
}

data class ListItem(
    val id: Int,
    val title: String,
    val description: String,
    val photo: ImageBitmap?
)


class ListViewModel(
    private val loadImageFromAsset: LoadImageFromAsset
) : ViewModel() {

    private val _items = MutableStateFlow(initialItems)
    val items = _items.asStateFlow()

    private val _bitmap = MutableStateFlow<Bitmap?>(null)
    val bitmap = _bitmap.asStateFlow()

    init {
        viewModelScope.launch {
            _bitmap.value = loadImageFromAsset.loadImageFromAsset(FILE_NAME)
        }
    }
}

@Composable
fun ListItemScreenRoot(
    loadImageFromAsset: LoadImageFromAsset,
    modifier: Modifier = Modifier
) {
    val viewModel = viewModel { ListViewModel(loadImageFromAsset) }
    val items by viewModel.items.collectAsStateWithLifecycle()
    val bitmap by viewModel.bitmap.collectAsStateWithLifecycle()
    Homework1(
        items = items,
        assetBitmap = bitmap?.asImageBitmap(),
        modifier = modifier
    )
}

@Composable
fun Homework1(
    items: List<ListItem>,
    assetBitmap: ImageBitmap?,
    modifier: Modifier = Modifier
) {

    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
    ) {
        items(
            items = items,
            key = { it.id },
        ) { item ->

            ContextualListItem(
                item = item,
                imageBitmap = assetBitmap,
                modifier = Modifier
                    .fillMaxWidth(),
                actions = {
                    listOf(
                        Icons.Default.Star,
                        Icons.Default.Phone,
                        Icons.Default.Edit,
                    ).forEach { icon ->
                        IconButton(
                            onClick = {}
                        ) {
                            Icon(
                                imageVector = icon,
                                contentDescription = null
                            )
                        }
                    }
                }
            )
        }
    }
}

@SuppressLint("UseOfNonLambdaOffsetOverload")
@Composable
private fun ContextualListItem(
    item: ListItem,
    imageBitmap: ImageBitmap?,
    actions: @Composable RowScope.() -> Unit,
    modifier: Modifier = Modifier
) {
    var contextMenuWidth by remember {
        mutableFloatStateOf(0f)
    }

    val offset = remember {
        Animatable(initialValue = 0f)
    }

    val scope = rememberCoroutineScope()

    val density = LocalDensity.current

    var isContextMenuVisible by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(isContextMenuVisible, contextMenuWidth) {
        if (isContextMenuVisible) {
            offset.animateTo(contextMenuWidth)
        } else {
            offset.animateTo(0f)
        }
    }

    Box(
        modifier = modifier
            .height(IntrinsicSize.Min),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxHeight()
                .background(Color.Green)
                .onSizeChanged { contextMenuWidth = it.width.toFloat() }
        ) {
            actions()
        }
        ListItem(
            headlineContent = { Text(item.title) },
            supportingContent = { Text(item.description) },
            leadingContent = {
                imageBitmap?.let {
                    Image(
                        bitmap = it,
                        contentDescription = null,
                        modifier = Modifier
                            .size(100.dp),
                        contentScale = ContentScale.Crop
                    )
                }
            },
            modifier = Modifier
                .offset {
                    IntOffset(
                        x = with(density) {
                            offset.value
                                .toDp()
                                .roundToPx()
                        },
                        y = 0
                    )
                }
                .background(Color.Green)
                .pointerInput(item.id) {
                    detectHorizontalDragGestures(
                        onHorizontalDrag = { _, dragAmount ->
                            scope.launch {
                                val newOffset = (offset.value + dragAmount)
                                    .coerceIn(0f, contextMenuWidth)
                                offset.snapTo(newOffset)
                            }
                        },
                        onDragEnd = {
                            when {
                                offset.value >= contextMenuWidth / 2f -> {
                                    scope.launch {
                                        offset.animateTo(contextMenuWidth)
                                        isContextMenuVisible = true
                                    }
                                }

                                else -> {
                                    scope.launch {
                                        offset.animateTo(0f)
                                        isContextMenuVisible = false
                                    }
                                }
                            }
                        }
                    )
                }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun Homework1Preview() {

}