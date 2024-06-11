package com.example.uread.core.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp


@Composable
fun Shelves(
    selectedTab: Int,
    onTabSelected: (Int) -> Unit
) {
    val shelves by remember { mutableStateOf(mutableListOf("All Books")) }
    ScrollableTabRow(
        selectedTabIndex = selectedTab,
        edgePadding = 16.dp
    ) {
        shelves.forEachIndexed { index, title ->
            Tab(
                text = { Text(title) },
                selected = selectedTab == index,
                onClick = { onTabSelected(index) }
            )
        }
        Tab(
            icon = { Icon(imageVector = Icons.Filled.Add, contentDescription = "New shelf") },
            selected = false,
            onClick = {
                shelves.add("Shelf ${shelves.size + 1} ")
                onTabSelected(shelves.size - 1)
            }
        )
    }
}