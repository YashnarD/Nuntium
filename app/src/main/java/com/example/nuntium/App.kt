package com.example.nuntium

import android.app.Application
import com.example.nuntium.di.component.AppComponent
import com.example.nuntium.di.component.DaggerAppComponent

class App : Application() {
    companion object {
         var appComponent: AppComponent = DaggerAppComponent.builder().build()
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().build()
    }
}