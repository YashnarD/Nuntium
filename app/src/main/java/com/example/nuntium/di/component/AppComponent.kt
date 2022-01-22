package com.example.nuntium.di.component

import androidx.fragment.app.Fragment
import com.example.nuntium.MainActivity
import com.example.nuntium.di.module.DatabaseModule
import com.example.nuntium.di.module.NetworkModule
import com.example.nuntium.fragments.Homepage
import com.example.nuntium.fragments.main.ArticlePage
import com.example.nuntium.fragments.main.HomeFragment
import com.example.nuntium.fragments.main.SeeAllFragment
import com.example.nuntium.fragments.main.ViewPagerFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, DatabaseModule::class])
interface AppComponent {

    fun inject(viewPagerFragment: ViewPagerFragment)
    fun inject(homeFragment: HomeFragment)
    fun inject(mainActivity: MainActivity)
    fun inject(homepage: Homepage)
    fun inject(seeAllFragment: SeeAllFragment)
}