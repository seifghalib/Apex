package com.example.apexapp.network

import com.example.apexapp.apidata.NewsApi
import retrofit2.Call
import retrofit2.http.GET

interface NewsApiInterface {

    @get:GET("v2/top-headlines?country=us&category=entertainment&apiKey=fb8fc9e1395844a1994fafa98a279639")
    val news: Call<NewsApi>
}