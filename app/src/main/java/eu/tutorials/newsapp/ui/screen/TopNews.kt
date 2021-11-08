package eu.tutorials.newsapp.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import eu.tutorials.newsapp.MockData
import eu.tutorials.newsapp.NewsData

@Composable
fun TopNews(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize(),horizontalAlignment = Alignment.CenterHorizontally) {
      Text(text = "Top News",fontWeight = FontWeight.SemiBold)
        //Todo 6: Replace the Button with LazyColumn and pass in the list from mockdata
        LazyColumn{
            items(MockData.topNewsList){newsData->
                //Todo 7: Use TopNewsItem as the UI and pass in the result from the items
                TopNewsItem(newsData = newsData)
            }
        }
    }
}

/**Todo 4: Create UI template for each news item
 * First we add a Box so we can have an image from each item on top as a background
 * Then we add a Column with padding top and start with vertical arrangement to space in between
 * In the Column we add a Text to show the published date and another Text to show the Title,
 * The date is not properly formatted so we will fix that in coming lectures
 * Next we add a Spacer to push the title towards to bottom
 */
@Composable
fun TopNewsItem(newsData: NewsData) {
    Box(modifier = Modifier.height(200.dp).padding(8.dp)) {
        Image(painter = painterResource(id = newsData.image), contentDescription ="",contentScale = ContentScale.FillBounds)
        Column(modifier = Modifier
            .wrapContentHeight()
            .padding(top = 16.dp, start = 16.dp),verticalArrangement = Arrangement.SpaceBetween) {
            Text(text = newsData.publishedAt,color = Color.White,fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(100.dp))
            Text(text = newsData.title,color = Color.White,fontWeight = FontWeight.SemiBold)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun TopNewsPreview() {
    //Todo 5: we add TopNewsItem in the Preview with a sample data to review the UI
    TopNewsItem(  NewsData(
        2,
        author = "Namita Singh",
        title = "Cleo Smith news — live: Kidnap suspect 'in hospital again' as 'hard police grind' credited for breakthrough - The Independent",
        description = "The suspected kidnapper of four-year-old Cleo Smith has been treated in hospital for a second time amid reports he was “attacked” while in custody.",
        publishedAt = "2021-11-04T04:42:40Z"
    ))
}