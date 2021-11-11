package eu.tutorials.newsapp.ui

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import eu.tutorials.newsapp.model.NewsResponse
import eu.tutorials.newsapp.network.Api
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

    init {
        getArticles()
        //getSearchedArticles(query.value)
    }

    private fun getArticles(){
        val service = Api.retrofitService.getTopArticles("us","d2691289ff474bb9850b71fa026ce470")
        service.enqueue(object :Callback<NewsResponse>{
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                if (response.isSuccessful){
                    _newsResponse.value = response.body()!!
                    Log.d("art2","${_newsResponse.value}")
                }else{
                    Log.d("art2","${response.code()}")
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
               Log.d("error","${t.printStackTrace()}")
            }

        })
    }

     fun getSearchedArticles(query: String){
        val service = Api.retrofitService.searchArticles(query,"d2691289ff474bb9850b71fa026ce470")
        service.enqueue(object :Callback<NewsResponse>{
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                if (response.isSuccessful){
                    _searchedNewsResponse.value = response.body()!!
                    Log.d("search","${_searchedNewsResponse.value}")
                }else{
                    Log.d("search","${response.code()}")
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                Log.d("searcherror","${t.printStackTrace()}")
            }

        })
    }

    fun onQueryChanged(query: String){
        this.query.value = query
    }
}