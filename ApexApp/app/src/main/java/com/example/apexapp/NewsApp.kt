package com.example.apexapp

import android.app.Application
import com.example.apexapp.dagger.AppComponent
import com.example.apexapp.dagger.DaggerAppComponent

class NewsApp : Application() {

    var appComponent: AppComponent? = null
        private set

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }
}