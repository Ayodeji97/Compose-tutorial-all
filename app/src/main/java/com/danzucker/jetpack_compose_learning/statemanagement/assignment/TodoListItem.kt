package com.danzucker.jetpack_compose_learning.statemanagement.assignment

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.danzucker.jetpack_compose_learning.ui.theme.Jetpack_Compose_LearningTheme

@Composable
fun TodoScreenRoot(
    modifier: Modifier = Modifier
) {
    val viewModel = viewModel<TodoViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()

    TodoScreen(
        state = state,
        onAction = viewModel::onAction,
        modifier = modifier
    )
}


@Composable
private fun TodoScreen(
    state: TodoState,
    onAction: (TodoAction) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        LazyColumn(
            modifier = Modifier
                .weight(1f)
        ) {
            items(items = state.todoList, key = { it.id }) {
                TodoListItem(
                    todo = it,
                    onAction = onAction
                )
            }
        }

        TodoInputFields(
            titleText = state.titleText,
            descriptionText = state.descriptionText,
            onTitleChange = { newText ->
                onAction(TodoAction.OnTitleTextChange(newText))
            },
            onDescriptionChange = { newText ->
                onAction(TodoAction.OnDescriptionTextChange(newText))
            },
            onAddTodoClick = {
                onAction(TodoAction.OnAddTodoClicked)
            }
        )
    }
}


@Composable
private fun TodoInputFields(
    titleText: String,
    descriptionText: String,
    onTitleChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onAddTodoClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Column(
            modifier = Modifier
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            TextField(
                value = titleText,
                onValueChange = { newText ->
                    onTitleChange(newText)
                },
                label = {
                    Text("Title")
                }
            )

            TextField(
                value = descriptionText,
                onValueChange = { newText ->
                    onDescriptionChange(newText)
                },
                label = {
                    Text("Description")
                }
            )
        }

        Button(
            onClick = onAddTodoClick
        ) {
            Text("Add")
        }
    }

}


@Composable
private fun TodoListItem(
    todo: Todo,
    onAction: (TodoAction) -> Unit,
    modifier: Modifier = Modifier
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {

        Column(
            modifier = Modifier
                .weight(1f)
        ) {
            val textDecoration = if (todo.isChecked) TextDecoration.LineThrough else TextDecoration.None
            Text(
                text = todo.title,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White,
                textDecoration = textDecoration,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                text = todo.description,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                color = Color.White,
                textDecoration = textDecoration,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }

        Checkbox(
            checked = todo.isChecked,
            onCheckedChange = { isChecked ->
                onAction(
                    TodoAction.OnCheckboxClicked(
                        id = todo.id,
                        isChecked = isChecked
                    )
                )
            },
            colors = CheckboxDefaults.colors(
                checkedColor = MaterialTheme.colorScheme.inversePrimary,
                uncheckedColor = Color.Gray,
                checkmarkColor = Color.White
            )
        )

        IconButton(
            onClick = {
                onAction(TodoAction.OnDeleteIconClicked(id = todo.id))
            }
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete",
                tint = Color.LightGray
            )
        }
    }
}


@Preview(
    showBackground = true
)
@Composable
private fun TodoScreenPreview() {
    Jetpack_Compose_LearningTheme {
        val sampleTodoList = listOf(
            Todo(
                id = 1,
                title = "Title 1",
                description = "Description 1"
            ),
        )

        TodoScreen(
            state = TodoState(
                todoList = sampleTodoList
            ),
            onAction = {}
        )
    }
}