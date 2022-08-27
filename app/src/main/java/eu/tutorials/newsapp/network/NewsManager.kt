package eu.tutorials.newsapp.network

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
        val service = Api.retrofitService.getTopArticles("us", Api.API_KEY)
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