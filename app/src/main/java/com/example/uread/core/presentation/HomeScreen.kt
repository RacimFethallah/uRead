package com.example.uread.core.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.uread.books.presentation.book_shelf.ShelfPageScreen
import com.example.uread.core.presentation.components.Shelves


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {

    var selectedTab by remember { mutableIntStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(title = {
                when (selectedTab) {
                    0 -> Text("uRead")
                    else -> Text("Shelf ${selectedTab + 1}")
                }
            })
        },
        floatingActionButton = {
            FloatingActionButton(
                content = { Icon(Icons.Filled.Add, contentDescription = "Add") },
                onClick = { /*TODO*/ }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Shelves(selectedTab) { index: Int ->
                selectedTab = index
            }
            ShelfPageScreen(selectedTab)
        }
    }
}