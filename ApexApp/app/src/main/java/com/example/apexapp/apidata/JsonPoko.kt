package com.example.apexapp.apidata

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.ArrayList

data class NewsApi(@SerializedName("status") @Expose val status: String,
                   @SerializedName("totalResults") @Expose val totalResults: Int,
                   @SerializedName("articles") @Expose val articles: List<Article> = ArrayList())

data class Source(@SerializedName("id") @Expose val id : String,
                  @SerializedName("name") @Expose val name : String)

class Article(@SerializedName("source") @Expose val source: Source,
              @SerializedName("author") @Expose val author: String,
              @SerializedName("title") @Expose val title: String,
              @SerializedName("description") @Expose val description: String,
              @SerializedName("url") @Expose val url: String,
              @SerializedName("urlToImage") @Expose val urlToImage: String,
              @SerializedName("publishedAt") @Expose val publishedAt: String,
              @SerializedName("content") @Expose val content: String)