package eu.tutorials.newsapp.network

import eu.tutorials.newsapp.model.NewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("top-headlines")
    fun getTopArticles(@Query("country") country:String,@Query("apiKey")apiKey:String):Call<NewsResponse>


}