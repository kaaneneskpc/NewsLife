package eu.tutorials.newsapp.ui

import eu.tutorials.newsapp.model.NewsResponse
import eu.tutorials.newsapp.network.NewsService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext



class NewsManager(private val newsService: NewsService) {

    suspend fun getArticles(country: String): NewsResponse = withContext(Dispatchers.IO) {
        newsService.getTopArticles(country = country)
     }

    suspend fun getSearchedArticles(query: String): NewsResponse = withContext(Dispatchers.IO) {
        newsService.getSearchedArticles(query)
    }

    suspend fun getArticlesByCategory(category: String="business"): NewsResponse = withContext(Dispatchers.IO) {
        newsService.getCategories(category = category)
    }

    suspend fun getArticleBySource(source: String): NewsResponse = withContext(Dispatchers.IO) {
        newsService.getArticlesBySource(source)
    }
}