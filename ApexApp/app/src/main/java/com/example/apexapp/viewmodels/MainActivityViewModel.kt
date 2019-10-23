package com.example.apexapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.apexapp.apidata.Article

import javax.inject.Inject

class MainActivityViewModel @Inject
constructor(private val repository: ArticlesRepository) : ViewModel() {

    val listMutableLiveData: MutableLiveData<List<Article>>?
        get() = repository.getArticles()
}
