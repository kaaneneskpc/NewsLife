package eu.tutorials.newsapp.network

import eu.tutorials.newsapp.model.NewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("top-headlines")
    suspend fun getTopArticles(@Query("country") country:String): NewsResponse

    @GET("everything")
    suspend fun getArticlesBySource(@Query("sources") source:String): NewsResponse

    @GET("everything")
    suspend fun getSearchedArticles(@Query("q") query:String): NewsResponse

    @GET("top-headlines")
    suspend fun getCategories(@Query("category") category:String): NewsResponse
}