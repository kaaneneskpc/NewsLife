package eu.tutorials.newsapp.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController


//Todo 10: create a navcontroller parameter
@Composable
fun TopNews(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize(),horizontalAlignment = Alignment.CenterHorizontally) {
      Text(text = "Top News",fontWeight = FontWeight.SemiBold)
        //Todo 8: Add a Button to go the detail screen
        Button(onClick = {
            //Todo 11 use navController to navigate to the Detail screen
            navController.navigate("Detail") }) {
            Text(text = "Go to Detail Screen")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun TopNewsPreview() {
    TopNews(rememberNavController())
}