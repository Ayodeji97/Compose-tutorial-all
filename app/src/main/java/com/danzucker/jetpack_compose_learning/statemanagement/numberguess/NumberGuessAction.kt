package com.danzucker.jetpack_compose_learning.statemanagement.numberguess

sealed interface NumberGuessAction {
    data object OnGuessButtonClick : NumberGuessAction
    data class OnNumberTextChange(val number: String) : NumberGuessAction
    data object OnResetButtonClick : NumberGuessAction
}