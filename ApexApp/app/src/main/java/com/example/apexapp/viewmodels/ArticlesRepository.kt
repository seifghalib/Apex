package com.example.apexapp.viewmodels


import android.util.Log

import androidx.lifecycle.MutableLiveData
import com.example.apexapp.apidata.Article
import com.example.apexapp.apidata.NewsApi
import com.example.apexapp.network.NewsApiInterface

import java.util.Locale

import javax.inject.Inject

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit


private const val TAG = "NewsRepositoryTAG_"

class ArticlesRepository @Inject
constructor(private val retrofit: Retrofit) {

    private val articlesMutableLiveData = MutableLiveData<List<Article>>()

    fun getArticles():  MutableLiveData<List<Article>> {

        val newsApiInterface = retrofit.create(NewsApiInterface::class.java)
        val call = newsApiInterface.news

        call.enqueue(object : Callback<NewsApi> {
            override fun onResponse(call: Call<NewsApi>, response: Response<NewsApi>) {
                if (!response.isSuccessful) {
                    Log.d(TAG, "onResponse: " + String.format(Locale.getDefault(), "Code : %d", response.code()))
                    return
                }
                val body = response.body()?.articles
                body?.let {
                    articlesMutableLiveData.value = it
                }
            }
            override fun onFailure(call: Call<NewsApi>, t: Throwable) {
                Log.d(TAG, "onFailure: " + t.message)
            }
        })
        return articlesMutableLiveData
    }
}
