package com.danzucker.jetpack_compose_learning.statemanagement.numberguess

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.random.Random

class NumberGuessViewModel : ViewModel() {

    private var randomNumber = Random.nextInt(1, 101)
    private var attempts = 0

    private val _state = MutableStateFlow(NumberGuessState())
    val state = _state.asStateFlow()

    fun onAction(action: NumberGuessAction) {
        when (action) {
            is NumberGuessAction.OnGuessButtonClick -> {
              val guessNumber = state.value.numberText.toIntOrNull()
              if (guessNumber != null) {
                  attempts++
              }

              _state.update {
                  it.copy(
                        guessText = when {
                            guessNumber == null -> "Please enter a number"
                            guessNumber < randomNumber -> "You guessed too low, try again"
                            guessNumber > randomNumber -> "You guessed too high, try again"
                            else -> "Correct! It took you $attempts attempts"
                        },
                        isGuessCorrect = guessNumber == randomNumber,
                        numberText = ""
                  )
              }
            }
            is NumberGuessAction.OnNumberTextChange -> {
                _state.update {
                    it.copy(
                        numberText = action.number
                    )
                }
            }
            is NumberGuessAction.OnResetButtonClick -> {
                randomNumber = Random.nextInt(1, 101)
                attempts = 0
                _state.update {
                    it.copy(
                        numberText = "",
                        guessText = null,
                        isGuessCorrect = false
                    )
                }
            }
        }
    }
}