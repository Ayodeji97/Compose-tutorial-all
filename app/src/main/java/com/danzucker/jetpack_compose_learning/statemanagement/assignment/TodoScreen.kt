package com.danzucker.jetpack_compose_learning.statemanagement.assignment

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
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
fun TodoScreen(
    state: TodoState,
    onAction: (TodoAction) -> Unit,
    modifier: Modifier = Modifier
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {

        Column {
            Text(
                text = state.title,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black,
                textDecoration = if (state.isChecked) TextDecoration.LineThrough else TextDecoration.None
            )

            Text(
                text = state.description,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black,
                textDecoration = if (state.isChecked) TextDecoration.LineThrough else TextDecoration.None
            )
        }

        Checkbox(
            checked = state.isChecked,
            onCheckedChange = {
                onAction(TodoAction.OnCheckboxClicked)
            },
            colors = CheckboxDefaults.colors(
                checkedColor = MaterialTheme.colorScheme.inversePrimary,
                uncheckedColor = Color.Gray,
                checkmarkColor = Color.White
            )
        )
    }
}

@Preview(
    showBackground = true
)
@Composable
private fun TodoScreenPreview() {
    Jetpack_Compose_LearningTheme {
        TodoScreen(
            state = TodoState(
                title = "Bring out the trash",
                description = "Better do this before wife comes home.",
                isChecked = true
            ),
            onAction = {}
        )
    }
}