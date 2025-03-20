package com.danzucker.jetpack_compose_learning.statemanagement.assignment

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class TodoViewModel : ViewModel() {

    private val todoList = generateTodoList()
    private val _state = MutableStateFlow(TodoState(todoList = todoList))
    val state = _state.asStateFlow()

    fun onAction(action: TodoAction) {
        when (action) {
            is TodoAction.OnTitleTextChange -> {
                _state.update {
                    it.copy(
                        titleText = action.title
                    )
                }
            }

            is TodoAction.OnDescriptionTextChange -> {
                _state.update {
                    it.copy(
                        descriptionText = action.description
                    )
                }
            }

            is TodoAction.OnCheckboxClicked -> {
                _state.update { state ->
                    state.copy(
                        todoList = state.todoList.map {
                            if (it.id == action.id) {
                                it.copy(isChecked = action.isChecked)
                            } else {
                                it
                            }
                        }
                    )
                }
            }

            is TodoAction.OnDeleteIconClicked -> {
                val itemToDelete = state.value.todoList.firstOrNull { it.id == action.id }
                if (itemToDelete != null) {
                    _state.update {
                        it.copy(
                            todoList = it.todoList - itemToDelete
                        )
                    }
                }
            }

            TodoAction.OnAddTodoClicked -> {
                val newTodo = Todo(
                    id = state.value.todoList.size + 1,
                    title = state.value.titleText,
                    description = state.value.descriptionText
                )

                _state.update {
                    it.copy(
                        todoList = it.todoList + newTodo,
                        titleText = "",
                        descriptionText = ""
                    )
                }
            }
        }
    }

    private fun generateTodoList(): List<Todo> {
        return List(20) { index ->
            Todo(
                id = index + 1,
                title = "Todo item ${index + 1}",
                description = "Todo description ${index + 1}"
            )
        }
    }
}