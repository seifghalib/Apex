package com.example.apexapp.dagger

import com.example.apexapp.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, UiModule::class])
interface AppComponent {

    fun inject(activity: MainActivity)
}