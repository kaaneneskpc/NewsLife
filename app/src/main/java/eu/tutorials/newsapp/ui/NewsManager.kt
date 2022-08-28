package eu.tutorials.newsapp.ui

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import eu.tutorials.newsapp.data.ArticleCategory
import eu.tutorials.newsapp.data.getArticleCategory
import eu.tutorials.newsapp.model.NewsResponse
import eu.tutorials.newsapp.network.Api
import eu.tutorials.newsapp.network.NewsService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class NewsManager {

    private val _newsResponse =
        mutableStateOf(NewsResponse())
    val newsResponse:MutableState<NewsResponse>
    @Composable get() = remember {
        _newsResponse
    }

    val query = mutableStateOf("")

    private val _searchedNewsResponse =
        mutableStateOf(NewsResponse())
    val searchedNewsResponse:MutableState<NewsResponse>
        @Composable get() = remember {
            _searchedNewsResponse
        }

    private val _getArticleByCategory =
        mutableStateOf(NewsResponse())
    val getArticleByCategory:MutableState<NewsResponse>
        @Composable get() = remember {
            _getArticleByCategory
        }

    val selectedCategory:MutableState<ArticleCategory?> = mutableStateOf(null)

init {
    getArticles()
}
     fun getArticles(){
        val client =Api.retrofitService.getTopArticles("us")
        client.enqueue(object :Callback<NewsResponse>{
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                response.takeIf { it.isSuccessful }?.apply {
                    _newsResponse.value = response.body()!!
                    Log.d("art2","${_newsResponse.value}")
                }?:run {
                    Log.d("art2","${response.code()}")
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
               Log.d("error","${t.printStackTrace()}")
            }

        })
    }

     fun getSearchedArticles(query: String){
        val client = Api.retrofitService.searchArticles(query)
        client.enqueue(object :Callback<NewsResponse>{
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                response.takeIf { it.isSuccessful }?.apply {
                    _searchedNewsResponse.value = response.body()!!
                    Log.d("search","${_searchedNewsResponse.value}")
                }?:run {
                    Log.d("search","${response.code()}")
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                Log.d("searcherror","${t.printStackTrace()}")
            }

        })
    }

    fun getArticlesByCategory(category: String="business"){
        val client = Api.retrofitService.getCategories(category)
        client.enqueue(object :Callback<NewsResponse>{
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                response.takeIf { it.isSuccessful }?.apply {
                    _getArticleByCategory.value = response.body()!!
                    Log.d("carte","${_getArticleByCategory.value}")
                }?:run {
                    Log.d("carte","${response.code()}")
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                Log.d("searcherror","${t.printStackTrace()}")
            }

        })
    }

    fun onSelectedCategoryChanged(category:String){
        val newCategory = getArticleCategory(category = category)
        selectedCategory.value = newCategory
    }

    fun onQueryChanged(query: String){
        this.query.value = query
    }

}