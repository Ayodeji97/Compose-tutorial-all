package com.danzucker.jetpack_compose_learning.personalplayground

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.danzucker.jetpack_compose_learning.R
import com.danzucker.jetpack_compose_learning.ui.theme.Jetpack_Compose_LearningTheme

@Composable
fun ModifierPlayGround(
    modifier: Modifier = Modifier
) {

    Box(
        modifier = modifier
            .sizeIn(
                minWidth = 100.dp,
                maxWidth = 300.dp,
                minHeight = 100.dp,
                maxHeight = 200.dp
            )
            .background(Color.Red)
    ) {
        Box(
            modifier = Modifier
                .size(150.dp)
                .background(Color.Green)
        ) {
            Box(
                modifier = Modifier
                    .size(100.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_launcher_background),
                    contentDescription = null,
                    modifier = Modifier
                        .clip(CircleShape)
                        .align(Alignment.BottomEnd)
                        .offset(x = 20.dp, y = 20.dp)
                        .padding(10.dp)
                        .background(Color.Blue)
                        .offset(x = 10.dp, y = 10.dp)
                )
            }
        }
    }


}

@Preview
@Composable
private fun ModifierPlayGroundPreview() {
    Jetpack_Compose_LearningTheme {
        ModifierPlayGround()
    }
}