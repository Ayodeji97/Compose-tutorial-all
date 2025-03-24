package com.danzucker.jetpack_compose_learning.advancelayout.utils

import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout

fun Modifier.printConstraints(tag: String): Modifier {
    return layout { measurable, constraints ->
        println("$tag: $constraints")
        val placeable = measurable.measure(
            constraints = constraints
        )

        layout(placeable.width, placeable.height) {
            placeable.place(0, 0)
        }
    }
}