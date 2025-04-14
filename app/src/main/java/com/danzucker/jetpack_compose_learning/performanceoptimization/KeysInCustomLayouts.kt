package com.danzucker.jetpack_compose_learning.performanceoptimization

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.danzucker.jetpack_compose_learning.ui.theme.Jetpack_Compose_LearningTheme

data class Section(
    val id: Int,
    val header: String,
    val description: String
)

@Composable
fun KeysCustomLayout(modifier: Modifier = Modifier) {
    var sections by remember {
        mutableStateOf(
            (1..3).map {
                Section(
                    id = it,
                    header = "Section $it Header",
                    description = "Section $it description"
                )
            }
        )
    }
    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        for (section in sections) {
            /**
             * In cases where you have a list where a change in one item should not
             * cause recomposition of the other items, you can use the key function to
             * if you are not in a lazy list, that provide that by default to optimise this.
             */
            key(section.id) {
                Section(section)
            }
        }
        Button(
            onClick = {
                sections = sections.shuffled()
            }
        ) {
            Text("Shuffle")
        }
    }
}

@Composable
fun Section(
    section: Section,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = section.header,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = section.description,
        )
    }
}

@Preview
@Composable
private fun KeysCustomLayoutPreview() {
    Jetpack_Compose_LearningTheme {
        KeysCustomLayout(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        )
    }
}