package eu.tutorials.newsapp.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import eu.tutorials.newsapp.MockData
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
        //Todo 7: add a placeholder for the argument to be received
        composable("Detail/{newsId}",
            //Todo 8: provide the argument type usiing NavType
            arguments = listOf(
                navArgument("newsId") { type = NavType.IntType }
            )){navBackStackEntry->
            //Todo 9 receive the id from NavBackStackEntry, we use getInt since its pf Int type and pass in the key
            val id = navBackStackEntry.arguments?.getInt("newsId")
            //Todo 10:pass in the id to getNews created in MockData to retrieve selected news
            val newsData = MockData.getNews(id)
            //Todo 11: provide newsData as a value to detail screen
            DetailScreen(navController,newsData)
        }
    }
}