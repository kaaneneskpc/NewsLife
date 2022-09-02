package eu.tutorials.newsapp.network

import eu.tutorials.newsapp.ui.NewsManager

class NewsRepository(val newsManager: NewsManager) {
    suspend fun getArticles() =  newsManager.getArticles("us")
    suspend fun getArticlesByCategory(category: String) = newsManager.getArticlesByCategory(category = category)
    suspend fun getArticlesBySource(source: String) = newsManager.getArticleBySource(source = source)
    suspend fun getSearchedArticles(query: String) = newsManager.getSearchedArticles(query = query)
}