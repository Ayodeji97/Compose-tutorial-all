package com.danzucker.jetpack_compose_learning.basiclayout


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.danzucker.jetpack_compose_learning.ui.theme.BackgroundColor
import com.danzucker.jetpack_compose_learning.ui.theme.Jetpack_Compose_LearningTheme


@Composable
fun BasicLayoutAssignment(
    title: String,
    description: String,
    date: String,
    modifier: Modifier = Modifier
) {

    // Structure of the design
    // Column -> Row -> Text -> Text
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(BackgroundColor, shape = RoundedCornerShape(5.dp))
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Outlined.CheckCircle,
                contentDescription = "Check Circle",
                tint = Color.White
            )

            Text(
                text = title,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier
                    .weight(1f)
            )

            Icon(
                imageVector = Icons.Default.Menu, // still need to change this icon
                contentDescription = "Menu",
                tint = Color.White,
            )
        }

        Text(
            text = description,
            fontSize = 16.sp,
            color = Color.White,
            modifier = Modifier
                .padding(
                    top = 16.dp,
                    start = 40.dp
                )
        )

        Text(
            text = date,
            fontSize = 18.sp,
            color = Color.White.copy(alpha = 0.8f),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            textAlign = TextAlign.End
        )
    }
}

@Preview(
    device = Devices.NEXUS_10
)
@Composable
private fun BasicLayoutAssignmentPreview() {
    Jetpack_Compose_LearningTheme {
        BasicLayoutAssignment(
            title = "Project X",
            description = "Project X is a project management tool that helps you organize your work and collaborate with your team online.",
            date = "Mar 5, 10:00",
        )
    }
}