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

//Todo 12: create a nav controller variable
@Composable
fun DetailScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize(),horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Detail Screen",fontWeight = FontWeight.SemiBold)
        //Todo 9: Add a Button to go the TopNews screen
        Button(onClick = {
            //Todo 13: use navcontroller to navigate to topnews
            //navController.navigate("TopNews")
            //Todo 16: use popBackStack to avoid duplicate screens
            navController.popBackStack()
        }) {
            Text(text = "Go Back to TopNews")
        }
    }
}



@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    DetailScreen(rememberNavController())
}