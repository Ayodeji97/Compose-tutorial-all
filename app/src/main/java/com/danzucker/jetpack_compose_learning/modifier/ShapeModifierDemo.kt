package com.danzucker.jetpack_compose_learning.modifier

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.danzucker.jetpack_compose_learning.R
import com.danzucker.jetpack_compose_learning.ui.theme.Jetpack_Compose_LearningTheme

@Composable
fun ShapeModifierDemo(modifier: Modifier = Modifier) {

    Image(
        painter = painterResource(R.drawable.living),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(200.dp)
            //.clip(TriangleShape)
            .clip(StarShape)
//            .clip(RoundedCornerShape(
//                percent = 50
//            ))
           // .clip(CircleShape)
    )
}

data object TriangleShape : Shape {

    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        return Outline.Generic(
            path = Path().apply {
                moveTo(
                    x = size.width / 2f,
                    y = 0f
                )
                lineTo(
                    x = 0f,
                    y = size.height
                )
                lineTo(
                    x = size.width,
                    y = size.height
                )
                close()
            }
        )
    }
}

// Assignment - Make a start shape with a custom shape
data object StarShape : Shape {

    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        return Outline.Generic(
            path = Path().apply {
                // Hardcoded points for a simple star shape
                val centerX = size.width / 2f
                val centerY = size.height / 2f
                val outerRadius = size.minDimension / 2f
                val innerRadius = outerRadius / 2.5f

                // Points of the star (alternating between outer and inner points)
                moveTo(centerX, centerY - outerRadius) // Top point
                lineTo(centerX + innerRadius, centerY - innerRadius) // Inner right
                lineTo(centerX + outerRadius, centerY) // Right point
                lineTo(centerX + innerRadius, centerY + innerRadius) // Inner bottom right
                lineTo(centerX, centerY + outerRadius) // Bottom point
                lineTo(centerX - innerRadius, centerY + innerRadius) // Inner bottom left
                lineTo(centerX - outerRadius, centerY) // Left point
                lineTo(centerX - innerRadius, centerY - innerRadius) // Inner top left
                close() // Connect back to the top point
            }
        )
    }
}



@Preview
@Composable
private fun ShapeModifierDemoPreview() {
    Jetpack_Compose_LearningTheme {
        ShapeModifierDemo()
    }
}
