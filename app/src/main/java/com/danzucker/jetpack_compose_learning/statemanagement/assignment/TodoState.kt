package com.danzucker.jetpack_compose_learning.statemanagement.assignment

data class TodoState(
    val titleText: String = "",
    val descriptionText: String = "",
    val todoList: List<Todo> = emptyList()
)

data class Todo(
    val id: Int = 0,
    val title: String = "",
    val description: String = "",
    val isChecked: Boolean = false,
)
