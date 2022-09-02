package eu.tutorials.newsapp.ui

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import eu.tutorials.newsapp.BottomMenuScreen
import eu.tutorials.newsapp.components.BottomMenu
import eu.tutorials.newsapp.model.Articles
import eu.tutorials.newsapp.network.Api
import eu.tutorials.newsapp.ui.screen.Categories
import eu.tutorials.newsapp.ui.screen.DetailScreen
import eu.tutorials.newsapp.ui.screen.Sources
import eu.tutorials.newsapp.ui.screen.TopNews
import eu.tutorials.newsapp.ui.viewModel.BaseViewModel

@Composable
fun NewsApp(baseViewModel: BaseViewModel) {
    val scrollState = rememberScrollState()
    val navController = rememberNavController()
    MainScreen(navController = navController,scrollState,mainViewModel = baseViewModel)
}

@Composable
fun MainScreen(navController: NavHostController,scrollState: ScrollState, mainViewModel: BaseViewModel) {
    Scaffold(bottomBar ={
        BottomMenu(navController = navController)
    }) {
        Navigation(navController =navController , scrollState =scrollState,paddingValues = it, mainViewModel = mainViewModel)
    }
}


@Composable
fun Navigation(
    navController:NavHostController,
    scrollState: ScrollState,
    mainViewModel: BaseViewModel,
    paddingValues: PaddingValues) {

    val loading by mainViewModel.isLoading.collectAsState()
    val error by mainViewModel.isError.collectAsState()
    val articles = mutableListOf<Articles>()
    val topArticles = mainViewModel.newsResponse.collectAsState().value.articles
    articles.addAll(topArticles ?: listOf())

    NavHost(
        navController = navController,
        startDestination = BottomMenuScreen.TopNews.route,
        modifier = Modifier.padding(paddingValues)
    ) {

        val queryState = mutableStateOf(mainViewModel.query.value)
        val isLoading = mutableStateOf(loading)
        val isError = mutableStateOf(error)
        bottomNavigation(navController = navController, articles, query = queryState, mainViewModel, isError = isError, isLoading = isLoading)
        composable("Detail/{index}",
            arguments = listOf(
                navArgument("index") { type = NavType.IntType }
            )) { navBackStackEntry ->
            val index = navBackStackEntry.arguments?.getInt("index")
            index.takeIf { it != null }?.apply {
                queryState.value.takeIf { it != ""}?.apply {
                    articles.clear()
                    articles.addAll(mainViewModel.searchedNewsResponse.value.articles ?: listOf(Articles()))
                } ?: run {
                    articles.clear()
                    articles.addAll(mainViewModel.newsResponse.value.articles ?: listOf(Articles()))
                }
                DetailScreen(articles = articles[index!!], scrollState, navController)
            }
        }
    }
}

fun NavGraphBuilder.bottomNavigation(
    navController: NavController,
    articles: List<Articles>,
    query: MutableState<String>,
    viewModel: BaseViewModel,
    isError:MutableState<Boolean>,
    isLoading:MutableState<Boolean>)
{
   composable(BottomMenuScreen.TopNews.route) {
        TopNews(navController = navController,articles,
            viewModel = viewModel,
            query = query,
            isError = isError,
            isLoading = isLoading
        )
    }
    composable(BottomMenuScreen.Categories.route) {
        viewModel.getArticlesByCategory("business")
        viewModel.onSelectedCategoryChanged("business")
        Categories(viewModel = viewModel, onFetchCategory = {
            viewModel.onSelectedCategoryChanged(it)
            viewModel.getArticlesByCategory(it)
        }, isLoading = isLoading, isError = isError)
    }
    composable(BottomMenuScreen.Sources.route) {
        Sources(viewModel = viewModel, isLoading = isLoading, isError = isError)
    }
}