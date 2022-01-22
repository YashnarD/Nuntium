package com.example.nuntium.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.nuntium.fragments.main.CategoriesFragment
import com.example.nuntium.fragments.main.HomeFragment
import com.example.nuntium.fragments.main.SaveFragment
import com.example.nuntium.fragments.main.UserFragment

class HomeViewPager(fragment: Fragment): FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 4
    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> {
                HomeFragment()
            }
            1 -> {
                CategoriesFragment()
            }
            2 -> {
                SaveFragment()
            }
            3 -> {
                UserFragment()
            }
            else -> {
                HomeFragment()
            }
        }
    }
}