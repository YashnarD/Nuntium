package com.example.nuntium

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.nuntium.fragments.Homepage
import com.example.nuntium.fragments.main.HomeFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val homeFragment = HomeFragment()

        App.appComponent.inject(homeFragment = homeFragment)
        App.appComponent.inject(homepage = Homepage())
        App.appComponent.inject(this)
    }
}