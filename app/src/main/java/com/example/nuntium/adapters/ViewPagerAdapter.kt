package com.example.nuntium.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.nuntium.fragments.main.ViewPagerFragment

class ViewPagerAdapter(fragment: Fragment, val titleList: List<String>): FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = titleList.size

    override fun createFragment(position: Int): Fragment {
        return ViewPagerFragment.newInstance(position)
    }
}