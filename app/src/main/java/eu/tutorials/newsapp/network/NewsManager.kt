package eu.tutorials.newsapp.network

import android.util.Log
import androidx.compose.runtime.*
import eu.tutorials.newsapp.data.ArticleCategory
import eu.tutorials.newsapp.data.getAllArticleCategory
import eu.tutorials.newsapp.data.getArticleCategory
import eu.tutorials.newsapp.model.NewsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsManager {

    private val _newsResponse = mutableStateOf(NewsResponse())
    val newsResponse: State<NewsResponse>
    @Composable get() = remember {
        _newsResponse
    }

    init {
        getArticles()
    }

    private fun getArticles() {
        val service = Api.retrofitService.getTopArticles("us")
        service.enqueue(object: Callback<NewsResponse> {
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                response.takeIf { it.isSuccessful }?.apply {
                    _newsResponse.value = response.body()!!
                    Log.d("error","${_newsResponse.value}")
                } ?: run {
                    Log.d("error","${response.errorBody()}")
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                Log.d("error","${t.printStackTrace()}")
            }

        })
    }
}