package com.danzucker.jetpack_compose_learning.statemanagement.assignment

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class TodoViewModel : ViewModel() {

    private val _state = MutableStateFlow(TodoState())
    val state = _state.asStateFlow()

    fun onAction(action: TodoAction) {
        when (action) {
            TodoAction.OnCheckboxClicked -> {
                _state.update {
                    it.copy(
                        isChecked = !it.isChecked
                    )
                }
            }
        }
    }
}