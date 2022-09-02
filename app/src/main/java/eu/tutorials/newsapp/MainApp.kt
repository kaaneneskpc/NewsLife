package eu.tutorials.newsapp

import android.app.Application
import eu.tutorials.newsapp.network.Api
import eu.tutorials.newsapp.network.NewsRepository
import eu.tutorials.newsapp.ui.NewsManager

class MainApp: Application() {
    private val newsManager by lazy {
        NewsManager(Api.retrofitService)
    }

    val newsRepository by lazy {
        NewsRepository(newsManager = newsManager)
    }
}