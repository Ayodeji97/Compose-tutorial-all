package com.danzucker.jetpack_compose_learning.compositionlocal

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.danzucker.jetpack_compose_learning.ui.theme.Jetpack_Compose_LearningTheme

/**
 * What is composition local?
 * This is like a global variable that is specific to your composition tree.
 */
@Composable
fun CompositionLocalDemo(
    modifier: Modifier = Modifier
) {
    Button(
        onClick = {},
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.Red
        )
    ) {
        CompositionLocalProvider(
            LocalContentColor provides Color.Green,
            //LocalTextStyle provides LocalTextStyle.current.copy(color = Color.Red)
        ) {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = null
            )
            Text("Button clicked")
        }

    }
}


@Composable
fun MyCustomTopBar(
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        CompositionLocalProvider(
            LocalContentColor provides Color.Red,
            LocalTextStyle provides LocalTextStyle.current.copy(
                color = Color.Red,
                fontWeight = FontWeight.Bold,
                fontSize = LocalTextStyle.current.fontSize * 2
            )
        ) {
            title()
        }
    }
}

val LocalShape = compositionLocalOf { CircleShape }

@Composable
fun MyCustomButtonShape(modifier: Modifier = Modifier) {
    Button(
        onClick = {},
        shape = LocalShape.current,
    ) {
        Text("My shaped button")
    }
}

@Preview
@Composable
private fun CompositionLocalDemoPreview() {
    Jetpack_Compose_LearningTheme {
        //CompositionLocalDemo()

//        MyCustomTopBar(
//            title = {
//                Text(
//                    text = "My Custom Top Bar",
//                    modifier = Modifier.padding(8.dp)
//                )
//            },
//            modifier = Modifier.padding(16.dp)
//        )

        MyCustomButtonShape(
            modifier = Modifier.padding(16.dp)
        )
    }
}