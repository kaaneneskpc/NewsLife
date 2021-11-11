package eu.tutorials.newsapp.ui.screen

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.skydoves.landscapist.coil.CoilImage
import eu.tutorials.newsapp.MockData
import eu.tutorials.newsapp.MockData.getTimeAgo
import eu.tutorials.newsapp.R
import eu.tutorials.newsapp.components.SearchView
import eu.tutorials.newsapp.model.Articles
import eu.tutorials.newsapp.ui.NewsManager

@Composable
fun TopNews(navController: NavController,articles: List<Articles>,
            query:MutableState<String>,newsManager: NewsManager) {
    val searchedText = query.value
    Column(modifier = Modifier.fillMaxSize(),horizontalAlignment = Alignment.CenterHorizontally) {
       SearchView(query,newsManager)
        val resultList = mutableListOf<Articles>()
        if (searchedText != "") {
            resultList.addAll(newsManager.searchedNewsResponse.value.articles ?: listOf(Articles()))
        }else{
            resultList.addAll(articles)
        }
               LazyColumn{
                   items(resultList.size){index->
                       TopNewsItem(articles = resultList[index],
                           onNewsClick = {  navController.navigate("Detail/${index}")}
                       )
                   }
               }
           }
}

@Composable
fun TopNewsItem(articles: Articles,onNewsClick: () -> Unit = {},) {
    Box(modifier = Modifier
        .height(200.dp)
        .padding(8.dp)
        .clickable {
            onNewsClick()
        }) {
        CoilImage(
            imageModel = articles.urlToImage,
            // Crop, Fit, Inside, FillHeight, FillWidth, None
            contentScale = ContentScale.Crop,
            // shows a placeholder ImageBitmap when loading.
            placeHolder = ImageBitmap.imageResource(R.drawable.breaking_news)
        )
        Column(modifier = Modifier
            .wrapContentHeight()
            .padding(top = 16.dp, start = 16.dp),verticalArrangement = Arrangement.SpaceBetween) {
            Text(text = MockData.stringToDate(articles.publishedAt?:"2021-11-05T13:32:14Z").getTimeAgo(),color = Color.White,fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(100.dp))
            Text(text = articles.title?:"Not Available",color = Color.White,fontWeight = FontWeight.SemiBold)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun TopNewsPreview() {
    TopNewsItem(  Articles(
        author = "Namita Singh",
        title = "Cleo Smith news — live: Kidnap suspect 'in hospital again' as 'hard police grind' credited for breakthrough - The Independent",
        description = "The suspected kidnapper of four-year-old Cleo Smith has been treated in hospital for a second time amid reports he was “attacked” while in custody.",
        publishedAt = "2021-11-04T04:42:40Z"
    )
    )
}