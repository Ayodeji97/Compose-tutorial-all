@file:OptIn(ExperimentalLayoutApi::class, ExperimentalAnimatableApi::class)

package com.danzucker.jetpack_compose_learning.animation

import androidx.compose.animation.core.AnimationVector2D
import androidx.compose.animation.core.DeferredTargetAnimation
import androidx.compose.animation.core.ExperimentalAnimatableApi
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ApproachLayoutModifierNode
import androidx.compose.ui.layout.ApproachMeasureScope
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.LookaheadScope
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.MeasureResult
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.round
import com.danzucker.jetpack_compose_learning.ui.theme.Jetpack_Compose_LearningTheme

@Composable
fun LookaheadLayoutAnimation(
    modifier: Modifier = Modifier
) {
    var horizontalArrangement by remember { mutableStateOf(Arrangement.Start) }
    var dpIncrement by remember { mutableStateOf(0.dp) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {

        AnimatedFlowRow(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = horizontalArrangement
        ) {
            Box(
                modifier = Modifier
                    .size(100.dp + dpIncrement)
                    .animateLayoutChanges(scope = this)
                    .background(Color.Red)
            )

            Box(
                modifier = Modifier
                    .size(100.dp + dpIncrement)
                    .animateLayoutChanges(scope = this)
                    .background(Color.Green)
            )

            Box(
                modifier = Modifier
                    .size(100.dp + dpIncrement)
                    .animateLayoutChanges(scope = this)
                    .background(Color.Blue)
            )
        }

        Spacer(Modifier.height(16.dp))

        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Button(onClick = {
                horizontalArrangement = Arrangement.Start
            }) {
                Text("Start")
            }

            Button(onClick = {
                horizontalArrangement = Arrangement.Center
            }) {
                Text("Center")
            }

            Button(onClick = {
                horizontalArrangement = Arrangement.End
            }) {
                Text("End")
            }

            Button(onClick = {
                horizontalArrangement = Arrangement.SpaceBetween
            }) {
                Text("SpaceBetween")
            }

            Button(onClick = {
                horizontalArrangement = Arrangement.SpaceAround
            }) {
                Text("SpaceAround")
            }

            Button(onClick = {
                horizontalArrangement = Arrangement.SpaceEvenly
            }) {
                Text("SpaceEvenly")
            }

            Button(onClick = {
                dpIncrement -= 10.dp
            }) {
                Text("Dec Dp")
            }

            Button(onClick = {
                dpIncrement += 10.dp
            }) {
                Text("Inc Dp")
            }
        }
    }
}


@Composable
fun AnimatedFlowRow(
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
    modifier: Modifier = Modifier,
    content: @Composable LookaheadScope.() -> Unit
) {
    LookaheadScope {
        FlowRow(
            modifier = modifier
                .fillMaxWidth(),
            horizontalArrangement = horizontalArrangement
        ) {
            content()
        }
    }
}


fun Modifier.animateLayoutChanges(
    scope: LookaheadScope,
    positionAnimation: DeferredTargetAnimation<IntOffset, AnimationVector2D> = DeferredTargetAnimation(IntOffset.VectorConverter),
    sizeAnimation: DeferredTargetAnimation<IntSize, AnimationVector2D> = DeferredTargetAnimation(IntSize.VectorConverter)
): Modifier {
    return then(
        AnimateLayoutChangesElement(
            lookaheadScope = scope,
            positionAnimation = positionAnimation,
            sizeAnimation = sizeAnimation
        )
    )
}

data class AnimateLayoutChangesElement(
    private val lookaheadScope: LookaheadScope,
    private val positionAnimation: DeferredTargetAnimation<IntOffset, AnimationVector2D>,
    private val sizeAnimation: DeferredTargetAnimation<IntSize, AnimationVector2D>
) : ModifierNodeElement<AnimationLayoutChangesNode>() {
    override fun create(): AnimationLayoutChangesNode {
        return AnimationLayoutChangesNode(
            lookaheadScope = lookaheadScope,
            positionAnimation = positionAnimation,
            sizeAnimation = sizeAnimation
        )
    }

    override fun update(node: AnimationLayoutChangesNode) = Unit
}

@OptIn(ExperimentalAnimatableApi::class)
class AnimationLayoutChangesNode(
    private val lookaheadScope: LookaheadScope,
    private val positionAnimation: DeferredTargetAnimation<IntOffset, AnimationVector2D>,
    private val sizeAnimation: DeferredTargetAnimation<IntSize, AnimationVector2D>
) : ApproachLayoutModifierNode, Modifier.Node() {

    override fun Placeable.PlacementScope.isPlacementApproachInProgress(lookaheadCoordinates: LayoutCoordinates): Boolean {
        val targetOffset = with(lookaheadScope) {
            lookaheadScopeCoordinates.localLookaheadPositionOf(lookaheadCoordinates)
        }

        positionAnimation.updateTarget(
            target = targetOffset.round(),
            coroutineScope = coroutineScope,
            animationSpec = tween(durationMillis = 3000)
        )
        return !positionAnimation.isIdle
    }
    override fun isMeasurementApproachInProgress(lookaheadSize: IntSize): Boolean {
        sizeAnimation.updateTarget(
            target = lookaheadSize,
            coroutineScope = coroutineScope,
            animationSpec = tween(durationMillis = 3000)
        )
        return !sizeAnimation.isIdle
    }

    override fun ApproachMeasureScope.approachMeasure(
        measurable: Measurable,
        constraints: Constraints
    ): MeasureResult {
        val (width, height) = sizeAnimation.updateTarget(
            target = lookaheadSize,
            coroutineScope = coroutineScope,
            animationSpec = tween(durationMillis = 3000)
        )

        val animatedConstraints = Constraints.fixed(width = width, height = height)
        val placeable = measurable.measure(animatedConstraints)

        return with(lookaheadScope) {
            layout(
                width = placeable.width,
                height = placeable.height
            ) {
                coordinates?.let {
                    // This give this predicted position of the element
                    val targetOffset = lookaheadScopeCoordinates.localLookaheadPositionOf(
                        sourceCoordinates = it
                    )
                    val animatedOffset = positionAnimation.updateTarget(
                        target = targetOffset.round(),
                        coroutineScope = coroutineScope,
                        animationSpec = tween(durationMillis = 3000)
                    )

                    // This gives the current position of the element
                    val currentOffset = lookaheadScopeCoordinates.localPositionOf(
                        sourceCoordinates = it
                    )

                    val (x, y) = animatedOffset - currentOffset.round()

                    placeable.place(x = x, y = y)
                } ?: placeable.place(x = 0, y = 0)
            }
        }
    }
}

@Preview
@Composable
private fun LookaheadLayoutAnimationDemo() {
    Jetpack_Compose_LearningTheme {
        LookaheadLayoutAnimation()
    }
}