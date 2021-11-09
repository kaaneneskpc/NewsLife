package eu.tutorials.newsapp

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Category
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Source
import androidx.compose.ui.graphics.vector.ImageVector

//Todo 2: create a sealed class as a template for each screen
sealed class BottomMenuScreen(val route: String, val icon: ImageVector, val title: String) {

    //Todo 4: create object for each screen on the navigation
    object TopNews : BottomMenuScreen("top news", icon = Icons.Outlined.Home, "Top News")
    object Categories : BottomMenuScreen("categories", icon = Icons.Outlined.Category, "Categories")
    object Sources : BottomMenuScreen("sources", icon = Icons.Outlined.Source, "Sources")
}
