package eu.tutorials.newsapp.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.skydoves.landscapist.coil.CoilImage
import eu.tutorials.newsapp.MockData
import eu.tutorials.newsapp.MockData.getTimeAgo
import eu.tutorials.newsapp.R
import eu.tutorials.newsapp.components.ErrorUI
import eu.tutorials.newsapp.components.LoadingUI
import eu.tutorials.newsapp.components.SearchView
import eu.tutorials.newsapp.model.Articles
import eu.tutorials.newsapp.ui.viewModel.BaseViewModel

@Composable
fun TopNews(navController: NavController,articles: List<Articles>,
            query:MutableState<String>,viewModel: BaseViewModel, isError:MutableState<Boolean>, isLoading:MutableState<Boolean>) {
    val searchedText = query.value
    Column(modifier = Modifier.fillMaxSize(),horizontalAlignment = Alignment.CenterHorizontally) {
       SearchView(query = query, viewModel = viewModel)
        val resultList = mutableListOf<Articles>()
        if (searchedText != "") {
            resultList.addAll(viewModel.searchedNewsResponse.collectAsState().value.articles ?: listOf(Articles()))
        }else{
            resultList.addAll(articles)
        }
        when {
            isLoading.value  -> { LoadingUI() }
            isError.value -> { ErrorUI() }
            else -> {
                LazyColumn{
                    items(resultList.size){index->
                        TopNewsItem(articles = resultList[index],
                            onNewsClick = {  navController.navigate("Detail/${index}")}
                        )
                    }
                }
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
            error = ImageBitmap.imageResource(R.drawable.breaking_news),
            placeHolder = ImageBitmap.imageResource(R.drawable.breaking_news)
        )
        Column(modifier = Modifier
            .wrapContentHeight()
            .padding(top = 16.dp, start = 16.dp),verticalArrangement = Arrangement.SpaceBetween) {
            articles.publishedAt?.let {
                Text(text = MockData.stringToDate(articles.publishedAt).getTimeAgo(),
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Spacer(modifier = Modifier.height(100.dp))
            articles.title?.let {
                Text(text = articles.title,
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun TopNewsPreview() {
    TopNewsItem(  Articles(
        author = "Namita Singh",
        title = "Cleo Smith news ??? live: Kidnap suspect 'in hospital again' as 'hard police grind' credited for breakthrough - The Independent",
        description = "The suspected kidnapper of four-year-old Cleo Smith has been treated in hospital for a second time amid reports he was ???attacked??? while in custody.",
        publishedAt = "2021-11-04T04:42:40Z"
    )
    )
}