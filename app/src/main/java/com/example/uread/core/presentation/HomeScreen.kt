package com.example.uread.core.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.uread.books.presentation.book_shelf.ShelfPageScreen
import com.example.uread.core.presentation.components.Shelves


@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun HomeScreen() {

    var selectedTab by remember { mutableIntStateOf(0) }
    val shelves = remember { mutableStateListOf("All Books") }
    val pagerState = rememberPagerState {
        shelves.size
    }


    var isExpanded by rememberSaveable { mutableStateOf(false) }

    val backgroundColor by animateColorAsState(
        if (isExpanded) Color.Black.copy(alpha = 0.6f) else Color.Transparent, label = ""
    )


    LaunchedEffect(selectedTab) {
        pagerState.animateScrollToPage(selectedTab)
    }

    LaunchedEffect(pagerState.currentPage, pagerState.isScrollInProgress) {
        if (!pagerState.isScrollInProgress) {
            selectedTab = pagerState.currentPage
        }
    }


    Scaffold(
        containerColor = backgroundColor,
        topBar = {
            TopAppBar(title = {
                when (selectedTab) {
                    0 -> Text("uRead")
                    else -> Text("Shelf $selectedTab")
                }
            })
        },
        floatingActionButton = {
            Column(
                modifier = Modifier
                    .wrapContentSize(),
                horizontalAlignment = Alignment.End,

            ) {
                AnimatedVisibility(
                    visible = isExpanded,
                    enter = fadeIn() + expandVertically(),
                    exit = fadeOut(),
                ) {
                    Column(
                        modifier = Modifier
                            .padding(4.dp),

                        horizontalAlignment = Alignment.End,
                    ) {
                        Row {
                            FilledTonalButton(
                                contentPadding = PaddingValues(8.dp),
                                shape = RoundedCornerShape(12.dp),
                                onClick = { /*TODO*/ }) {
                                Text("Search in Open Library")
                            }

                            Spacer(modifier = Modifier.width(16.dp))
                            SmallFloatingActionButton(
                                content = {
                                    Icon(
                                        Icons.Filled.Search,
                                        contentDescription = "Add 1"
                                    )
                                },
                                onClick = { /* Handle click */ },
                            )
                        }
                        Spacer(modifier = Modifier.height(5.dp))
                        Row {
                            FilledTonalButton(
                                contentPadding = PaddingValues(8.dp),
                                shape = RoundedCornerShape(12.dp),
                                onClick = { /*TODO*/ }) {
                                Text("Add Manually")
                            }
                            Spacer(modifier = Modifier.width(16.dp))
                            SmallFloatingActionButton(
                                content = { Icon(Icons.Filled.Add, contentDescription = "Add 2") },
                                onClick = { /* Handle click */ },
                            )
                        }
                    }

                }

                Spacer(modifier = Modifier.height(12.dp))

                    FloatingActionButton(
                        content = {
                            Icon(
                                if (isExpanded) Icons.Filled.Close else Icons.Filled.Add,
                                contentDescription = if (isExpanded) "Close" else "Add"
                            )
                        },
                        onClick = { isExpanded = !isExpanded }
                    )
                }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

            Shelves(shelves, selectedTab) { index: Int ->
                selectedTab = index
            }


            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) { index ->
                ShelfPageScreen(index)
            }


        }
    }
}


//if(isSheetOpen){
//    ModalBottomSheet(
//        sheetState = sheetState,
//        onDismissRequest = {
//            isSheetOpen = false
//        }
//    ) {
//        Button(
//            onClick = { /* Handle Add by Search */ },
//            colors = ButtonDefaults.buttonColors( Color.Blue),
//            shape = RoundedCornerShape(8.dp),
//            modifier = Modifier.padding(16.dp)
//        ) {
//            Text("Add by Search", color = Color.White)
//        }
//        Spacer(modifier = Modifier.height(8.dp))
//        Button(
//            onClick = { /* Handle Manual Add */ },
//            colors = ButtonDefaults.buttonColors(Color.Green),
//            shape = RoundedCornerShape(8.dp),
//            modifier = Modifier.padding(16.dp)
//        ) {
//            Text("Manual Add", color = Color.White)
//        }
//    }
//}