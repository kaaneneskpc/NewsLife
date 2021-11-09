package eu.tutorials.newsapp.ui

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import eu.tutorials.newsapp.MockData
import eu.tutorials.newsapp.components.BottomMenu
import eu.tutorials.newsapp.ui.screen.DetailScreen
import eu.tutorials.newsapp.ui.screen.TopNews

@Composable
fun NewsApp() {
    val scrollState = rememberScrollState()
    val navController = rememberNavController()
    //Todo 8: set MainScreen in the NewsApp
 MainScreen(navController = navController,scrollState)
}

//Todo 1: create a mainScreen composable
@Composable
fun MainScreen(navController: NavHostController,scrollState: ScrollState) {
    Scaffold(bottomBar ={
        BottomMenu(navController = navController)
    }) {
        //Todo 9: set Navigation in the MainScreen
        Navigation(navController =navController , scrollState =scrollState )
    }
}

@Composable
fun Navigation(navController:NavHostController,scrollState: ScrollState) {
    NavHost(navController = navController, startDestination ="TopNews") {
        composable("TopNews") {
            TopNews(navController = navController)
        }
        composable("Detail/{newsId}",
            arguments = listOf(
                navArgument("newsId") { type = NavType.IntType }
            )){navBackStackEntry->
            val id = navBackStackEntry.arguments?.getInt("newsId")
            val newsData = MockData.getNews(id)
            DetailScreen(newsData,scrollState,navController)
        }
    }
}