package com.example.nuntium.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.nuntium.R
import com.example.nuntium.databinding.FragmentHomepageBinding
import com.example.nuntium.fragments.main.CategoriesFragment
import com.example.nuntium.fragments.main.HomeFragment
import com.example.nuntium.fragments.main.SaveFragment
import com.example.nuntium.fragments.main.UserFragment
import nl.joery.animatedbottombar.AnimatedBottomBar
import androidx.fragment.app.FragmentActivity
import android.app.Activity
import android.os.Build
import android.view.MenuItem
import android.widget.AdapterView
import androidx.annotation.RequiresApi
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.nuntium.App
import com.example.nuntium.adapters.HomeViewPager
import com.google.android.material.navigation.NavigationBarView


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class Homepage : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    lateinit var binding: FragmentHomepageBinding
    private var myContext: FragmentActivity? = null
    lateinit var homeViewPager: HomeViewPager

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentHomepageBinding.inflate(inflater, container, false)
        App.appComponent.inject(this)


        binding.apply {
            homeViewPager = HomeViewPager(this@Homepage)
            viewPager.adapter = homeViewPager

            viewPager.isUserInputEnabled = false
            bottomNav.menu.findItem(R.id.tab_home).isChecked = true

            bottomNav.setOnItemSelectedListener {

                for (i in 0 until bottomNav.menu.size()) {
                    val item = bottomNav.menu.getItem(i)
                    val isChecked = item.itemId == it.itemId
                    item.isChecked = isChecked
                }

                when (it.itemId) {
                    R.id.tab_home -> {
                        viewPager.currentItem = 0
                    }
                    R.id.tab_categories -> {
                        viewPager.currentItem = 1
                    }
                    R.id.tab_save -> {
                        viewPager.currentItem = 2
                    }
                    R.id.tab_user -> {
                        viewPager.currentItem = 3
                    }
                }
                return@setOnItemSelectedListener true
            }


//            viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
//                override fun onPageSelected(position: Int) {
//                    when (position) {
//                        0 -> bottomNav.selectedItemId = R.id.tab_home
//                        1 -> bottomNav.selectedItemId = R.id.tab_categories
//                        2 -> bottomNav.selectedItemId = R.id.tab_save
//                        3 -> bottomNav.selectedItemId = R.id.tab_user
//                    }
//                }
//            })
        }

        return binding.root
    }

}