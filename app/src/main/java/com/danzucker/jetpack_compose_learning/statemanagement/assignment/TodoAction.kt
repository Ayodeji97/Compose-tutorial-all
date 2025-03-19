package com.danzucker.jetpack_compose_learning.statemanagement.assignment

sealed interface TodoAction {
    data object OnCheckboxClicked : TodoAction
}