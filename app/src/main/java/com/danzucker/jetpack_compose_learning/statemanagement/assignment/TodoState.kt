package com.danzucker.jetpack_compose_learning.statemanagement.assignment

data class TodoState(
    val title: String = "Bring out the trash",
    val description: String = "Better do this before wife comes home.",
    val isChecked: Boolean = false
)
