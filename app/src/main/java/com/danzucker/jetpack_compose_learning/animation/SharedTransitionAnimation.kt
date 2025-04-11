@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.danzucker.jetpack_compose_learning.animation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.danzucker.jetpack_compose_learning.R
import com.danzucker.jetpack_compose_learning.ui.theme.Jetpack_Compose_LearningTheme

/**
 * In case where you want to use shared transition in a lot of places in your app, you can
 * define it using CompositionLocalProvider and use it in your composable functions.
 * Please use this with care.
 */
val LocalSharedTransitionScope = staticCompositionLocalOf<SharedTransitionScope?> { null }


@Composable
fun SharedTransitionAnimation(
    modifier: Modifier = Modifier
) {
    var isExpanded by remember { mutableStateOf(false) }

    SharedTransitionLayout(
        modifier = modifier
    ) {
       // CompositionLocalProvider( LocalSharedTransitionScope provides this) {}
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                this@Column.AnimatedVisibility(
                    visible = !isExpanded,
                ) {

                    RowListItem(
                        title = "Sharon Monkka",
                        description = "Sharon Monkka is a cat",
                        onClick = {
                            isExpanded = !isExpanded
                        },
                        animatedVisibilityScope = this,
                        modifier = Modifier
                            .sharedBounds(
                                sharedContentState = rememberSharedContentState(key = "cat_item"),
                                animatedVisibilityScope = this
                            )
                    )
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                this@Column.AnimatedVisibility(
                    visible = isExpanded,
                ) {
                    VerticalListItem(
                        title = "Sharon Monkka",
                        description = "Sharon Monkka is a cat",
                        onClick = {
                            isExpanded = !isExpanded
                        },
                        animatedVisibilityScope = this,
                        modifier = Modifier
                            .sharedBounds(
                                sharedContentState = rememberSharedContentState(key = "cat_item"),
                                animatedVisibilityScope = this
                            )
                    )
                }
            }

        }
    }
}

@Composable
fun SharedTransitionScope.RowListItem(
    title: String,
    description: String,
    onClick: () -> Unit,
    animatedVisibilityScope: AnimatedVisibilityScope,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.LightGray)
            .clickable {
               onClick()
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    )  {
        Image(
            painter = painterResource(id = R.drawable.simple_cat_image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(100.dp)
                .sharedElement(
                    state = rememberSharedContentState(key = "cat_image"),
                    animatedVisibilityScope = animatedVisibilityScope
                ),
        )
        
        Column(
            modifier = Modifier
                .weight(1f)
        ) {
            Text(
                text = title,
                fontSize = 20.sp,
                modifier = Modifier
                    .sharedElement(
                        state = rememberSharedContentState(key = "cat_title"),
                        animatedVisibilityScope = animatedVisibilityScope
                    ),
            )

            Text(
                text = description,
                fontSize = 14.sp,
                modifier = Modifier
                    .sharedElement(
                        state = rememberSharedContentState(key = "cat_description"),
                        animatedVisibilityScope = animatedVisibilityScope
                    )
            )
        }
    }
}

@Composable
fun SharedTransitionScope.VerticalListItem(
    title: String,
    description: String,
    onClick: () -> Unit,
    animatedVisibilityScope: AnimatedVisibilityScope,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.LightGray)
            .clickable { onClick() },
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.simple_cat_image),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .sharedElement(
                    state = rememberSharedContentState(key = "cat_image"),
                    animatedVisibilityScope = animatedVisibilityScope
                ),
        )

        Text(
            text = title,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize()
                .sharedElement(
                    state = rememberSharedContentState(key = "cat_title"),
                    animatedVisibilityScope = animatedVisibilityScope
                )
        )

        Text(
            text = description,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize()
                .padding(bottom = 16.dp)
                .sharedElement(
                    state = rememberSharedContentState(key = "cat_description"),
                    animatedVisibilityScope = animatedVisibilityScope
                )


        )

    }
}

@Preview
@Composable
private fun SharedTransitionAnimationPreview() {
    Jetpack_Compose_LearningTheme {
        SharedTransitionAnimation()
//        RowListItem(
//            title = "Title",
//            description = "Description",
//            onClick = {},
//            modifier = Modifier
//                .padding(16.dp)
//        )
//        VerticalListItem(
//            title = "Title",
//            description = "Description",
//            onClick = {},
//        )
    }
}