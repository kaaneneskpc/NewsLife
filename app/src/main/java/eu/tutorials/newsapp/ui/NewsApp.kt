package eu.tutorials.newsapp.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import eu.tutorials.newsapp.ui.screen.DetailScreen
import eu.tutorials.newsapp.ui.screen.TopNews

@Composable
fun NewsApp() {
 Navigation()
}

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "TopNews") {
       composable("TopNews") {
            TopNews(navController = navController)
        }
        composable("Detail"){
            DetailScreen(navController)
        }
    }
}