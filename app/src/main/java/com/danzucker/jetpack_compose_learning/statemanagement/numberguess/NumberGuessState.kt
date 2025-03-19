package com.danzucker.jetpack_compose_learning.statemanagement.numberguess

data class NumberGuessState(
    val numberText: String = "",
    val guessText: String? = null,
    val isGuessCorrect: Boolean = false,
)
