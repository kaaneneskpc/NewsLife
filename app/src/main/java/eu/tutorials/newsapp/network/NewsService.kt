package eu.tutorials.newsapp.network

import eu.tutorials.newsapp.model.NewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("top-headlines")
    fun getTopArticles(@Query("country") country:String):Call<NewsResponse>

    @GET("everything")
    fun searchArticles(@Query("q") country:String):Call<NewsResponse>

    @GET("top-headlines")
    fun getCategories(@Query("category")category:String):Call<NewsResponse>
}