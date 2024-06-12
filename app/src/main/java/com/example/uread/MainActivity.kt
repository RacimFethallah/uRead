package com.example.uread

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.uread.books.presentation.search_book.SearchBookOpenLibrary
import com.example.uread.core.presentation.HomeScreen
import com.example.uread.ui.theme.UReadTheme
import com.example.uread.util.Navigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {


            UReadTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Navigation.HomeScreen.route
                ){
                    composable(
                        route = Navigation.HomeScreen.route
                    ){
                        HomeScreen(navController = navController)
                    }
                    composable(
                        route = Navigation.SearchBookOLScreen.route
                    ){
                        SearchBookOpenLibrary(navController = navController)
                    }
                }


            }
        }
    }
}







