package eu.tutorials.newsapp.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import eu.tutorials.newsapp.ui.screen.DetailScreen
import eu.tutorials.newsapp.ui.screen.TopNews

//Todo 1: create NewsApp file and create its composable
@Composable
fun NewsApp() {
    //Todo 2 pass in the Navigation() function where the screens and navigation is setup
 Navigation()
}

//Todo 3: create the Navigation function and create a navcontroller variable
@Composable
fun Navigation() {
    val navController = rememberNavController()
    //Todo 4 call the NavHost and pass in the navController with TopNews as the startdestination
    NavHost(navController = navController, startDestination = "TopNews") {
        //Todo 5:add two composable with the route and function for the two screens
        composable("TopNews") {
            //Todo 14: provide navcontroller value
            TopNews(navController = navController)
        }
        composable("Detail"){
            //Todo 15: provide nav controller value
            DetailScreen(navController)
        }
    }
}