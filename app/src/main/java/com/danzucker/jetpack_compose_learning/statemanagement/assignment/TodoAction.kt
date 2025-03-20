package com.danzucker.jetpack_compose_learning.statemanagement.assignment

sealed interface TodoAction {
    data class OnCheckboxClicked(val id: Int, val isChecked: Boolean) : TodoAction
    data class OnDeleteIconClicked(val id: Int) : TodoAction
    data class OnTitleTextChange(val title: String) : TodoAction
    data class OnDescriptionTextChange(val description: String) : TodoAction
    data object OnAddTodoClicked : TodoAction
}