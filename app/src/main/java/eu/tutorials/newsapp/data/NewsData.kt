package eu.tutorials.newsapp.data

import eu.tutorials.newsapp.R

data class NewsData(val id:Int, val image:Int = R.drawable.breaking_news, val author:String, val title:String, val description:String,val publishedAt:String)
