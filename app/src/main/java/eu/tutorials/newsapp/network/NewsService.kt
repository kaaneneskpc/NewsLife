package eu.tutorials.newsapp.network

import eu.tutorials.newsapp.model.NewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("top-headlines")
    fun getTopArticles(@Query("country") country:String):Call<NewsResponse>

    @GET("everything")
    fun getArticlesBySource(@Query("sources") source:String):Call<NewsResponse>

    @GET("everything")
    fun getSearchedArticles(@Query("q") source:String):Call<NewsResponse>

    @GET("top-headlines")
    fun getCategories(@Query("category") category:String):Call<NewsResponse>
}