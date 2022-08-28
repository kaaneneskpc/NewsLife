package eu.tutorials.newsapp.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.skydoves.landscapist.coil.CoilImage
import eu.tutorials.newsapp.MockData
import eu.tutorials.newsapp.MockData.getTimeAgo
import eu.tutorials.newsapp.R
import eu.tutorials.newsapp.data.getAllArticleCategory
import eu.tutorials.newsapp.model.Articles
import eu.tutorials.newsapp.ui.NewsManager

@Composable
fun Categories(onFetchCategory:(String)->Unit,newsManager: NewsManager) {
    val tabsItems = getAllArticleCategory()
    Column {
    LazyRow() {
        items(tabsItems.size) {
            val category = tabsItems[it]
            CategoryTab(
                category = category.category, onFetchCategory = onFetchCategory, isSelected =
                newsManager.selectedCategory.value == category
            )
        }
    }
        PagerContent(articles = newsManager.getArticleByCategory.value.articles?: listOf(Articles()))
    }
}

@Composable
fun CategoryTab(category: String,
                isSelected: Boolean = false,
                onFetchCategory: (String) -> Unit) {
    val background = if(isSelected) colorResource(id = R.color.purple_200) else colorResource(id =R.color.purple_700)
    Surface(
                    modifier = Modifier
                        .padding(horizontal = 4.dp, vertical = 16.dp)
                        .clickable {
                            onFetchCategory(category)
                        },
                    shape = MaterialTheme.shapes.small,
                    color = background
                ) {
                    Text(
                        text = category,
                        style = MaterialTheme.typography.body2,
                        color = Color.White,
                        modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 8.dp, bottom = 8.dp)
                    )

    }
}

@Composable
fun PagerContent(articles: List<Articles>,modifier:Modifier = Modifier.padding(8.dp)) {
    LazyColumn{
     items(articles){article->
         Card(modifier, border = BorderStroke(2.dp,color = colorResource(id = R.color.purple_500))
         ) {
           Row(modifier.fillMaxWidth()) {
               com.skydoves.landscapist.coil.CoilImage(
                   imageModel = article.urlToImage,
                   modifier = Modifier.size(100.dp),
                   placeHolder = painterResource(id = R.drawable.breaking_news),
                   error = painterResource(id = R.drawable.breaking_news)
                   )
               Column(modifier.padding(8.dp)) {
                   Text(text = article.title ?: "Not Available", fontWeight = FontWeight.Bold, maxLines = 3, overflow = TextOverflow.Ellipsis)
                   Row (modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                       Text(text = article.author?:"Not Available")
                       Text(text = MockData.stringToDate(article.publishedAt?:"2021-11-10T14:25:20Z").getTimeAgo())
                   }
               }
           }
         }
     }
    }
}