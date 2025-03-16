@file:OptIn(ExperimentalMaterial3WindowSizeClassApi::class)

package com.danzucker.jetpack_compose_learning.basiclayout

import android.app.Activity
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.danzucker.jetpack_compose_learning.ui.theme.Jetpack_Compose_LearningTheme

@Composable
fun NavigationRailsDemo(
    modifier: Modifier = Modifier
) {

    val activity = LocalActivity.current ?: error("No activity provided")
    val windowClass = calculateWindowSizeClass(activity = activity)
    val showNavigationRails = windowClass.widthSizeClass != WindowWidthSizeClass.Compact
    var selectedItemIndex by rememberSaveable {
        mutableIntStateOf(0)
    }

    Scaffold(
        bottomBar = {
            if (!showNavigationRails) {
                // NavigationBar()
            }
        }
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(
                    start = if (showNavigationRails) 80.dp else 0.dp
                )
        ) {
            items(100) {
                Text(
                    text = "Item $it",
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }

    if (showNavigationRails) {
        NavigationSideBar(
            items = bottomNavBarItems,
            selectedItemIndex = selectedItemIndex,
            onNavigate = { selectedItemIndex = it },
            modifier = Modifier
        )
    }
    
}


@Composable
fun NavigationSideBar(
    items: List<BottomNavigationItem>,
    selectedItemIndex: Int,
    onNavigate: (Int) -> Unit,
    modifier: Modifier = Modifier
) {

    NavigationRail(
        header = {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Menu"
                )
            }
            FloatingActionButton(
                onClick = {},
                elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add"
                )
            }
        },
        modifier = Modifier
            .background(MaterialTheme.colorScheme.inverseOnSurface)
            .offset(x = (-1).dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight(),
            verticalArrangement = spacedBy(16.dp, alignment = Alignment.Bottom)
        ) {
            items.forEachIndexed { index, bottomNavigationItem ->
                NavigationRailItem(
                    selected = selectedItemIndex == index,
                    onClick = { onNavigate(index) },
                    icon = {
                        NavigationIcon(
                            item = bottomNavigationItem,
                            selected = selectedItemIndex == index
                        )
                    },
                    label = {
                        Text(text = bottomNavigationItem.title)
                    }
                )
            }
        }
    }
}

@Composable
fun NavigationIcon(
    item: BottomNavigationItem,
    selected: Boolean,
    modifier: Modifier = Modifier
) {
    BadgedBox(
        badge = {
            if (item.badgeCount != null) {
                Badge {
                    Text(text = item.badgeCount.toString())
                }
            } else if (item.hasNews) {
                Badge()
            }
        }
    ) {
        Icon(
            imageVector = if (selected) {
                item.selectedIcon
            } else item.unSelectedIcon,
            contentDescription = item.title,
            modifier = modifier
        )
    }
}


@Preview
@Composable
private fun NavigationRailsDemoPreview() {
    Jetpack_Compose_LearningTheme {
        NavigationRailsDemo()
    }
}