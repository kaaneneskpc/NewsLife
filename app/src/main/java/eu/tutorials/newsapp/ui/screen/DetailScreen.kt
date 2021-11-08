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
import eu.tutorials.newsapp.MockData
import eu.tutorials.newsapp.NewsData
//Todo 3: create news data variable
@Composable
fun DetailScreen(navController: NavController,newsData: NewsData) {
    Column(modifier = Modifier.fillMaxSize(),horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Detail Screen",fontWeight = FontWeight.SemiBold)
        Button(onClick = {
            navController.popBackStack()
        }) {
            //Todo 4:set the author for each news on the button
                Text(text = "Go Back to TopNews + ${newsData.author}")
        }
    }
}


//Todo 5: provide a default news data for preview
@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    DetailScreen(rememberNavController(), NewsData(
        2,
        author = "Namita Singh",
        title = "Cleo Smith news — live: Kidnap suspect 'in hospital again' as 'hard police grind' credited for breakthrough - The Independent",
        description = "The suspected kidnapper of four-year-old Cleo Smith has been treated in hospital for a second time amid reports he was “attacked” while in custody.",
        publishedAt = "2021-11-04T04:42:40Z"
    ))
}