package eu.tutorials.newsapp

//Todo 1: create a data class for each news item
data class NewsData(val id:Int, val image:Int = R.drawable.breaking_news, val author:String, val title:String, val description:String,val publishedAt:String)
