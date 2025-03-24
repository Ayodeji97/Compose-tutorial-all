package com.danzucker.jetpack_compose_learning.advancelayout

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import com.danzucker.jetpack_compose_learning.ui.theme.Jetpack_Compose_LearningTheme

/**
 * There are 4 different kinds of constraints:
 * 1. Bounded constraints - When the constraints has specific values for width and height.
 * 2. Unbounded constraints - This is the default constraints when the constraints are not specified.
 * 3. Exact constraints - This means that the min width and max width are the same and the min height and max height are the same.
 * 4. Combined constraints - This is when the constraints are a combination of the above constraints.
  */

private val boundedConstraints = Constraints(
    minWidth = 50,
    maxWidth = 150,
    minHeight = 70,
    maxHeight = 200
)

private val unboundedConstraints = Constraints()

private val exactConstraints = Constraints(
    minWidth = 100,
    maxWidth = 100,
    minHeight = 200,
    maxHeight = 200
)

private val combinedConstraints = Constraints(
    minWidth = 50,
    maxWidth = 50,
    minHeight = 50,
    maxHeight = Constraints.Infinity
)


@Composable
fun ConstraintLayoutDemo(modifier: Modifier = Modifier) {

}

@Preview
@Composable
private fun ConstraintLayoutDemoPreview() {
    Jetpack_Compose_LearningTheme {
        ConstraintLayoutDemo()
    }
}