package com.example.nuntium.fragments.start

import android.os.Build
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.nuntium.R
import com.example.nuntium.databinding.FragmentOnboardingBinding
import com.example.nuntium.slide.SliderAdapter
import com.example.nuntium.slide.SliderItems
import kotlin.math.abs
import android.view.animation.DecelerateInterpolator

import android.view.animation.AlphaAnimation

import android.view.animation.Animation
import androidx.navigation.fragment.findNavController


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class Onboarding : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    lateinit var binding: FragmentOnboardingBinding
    lateinit var sliderAdapter: SliderAdapter

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentOnboardingBinding.inflate(inflater, container, false)
        activity?.window?.statusBarColor = ContextCompat.getColor(requireContext(), R.color.white)
        activity?.window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val list: MutableList<SliderItems> = mutableListOf()
        list.add(SliderItems(R.drawable.png))
        list.add(SliderItems(R.drawable.png))
        list.add(SliderItems(R.drawable.png))

        binding.viewPager2.adapter = SliderAdapter(list, binding.viewPager2)

        binding.viewPager2.clipToPadding = false
        binding.viewPager2.clipChildren = false
        binding.viewPager2.offscreenPageLimit = 3
        binding.viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(40))
        compositePageTransformer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = 0.85f + r * 0.15f
        }

        binding.buttonNext.setOnClickListener {
            findNavController().navigate(R.id.action_onboarding_to_welcomeScreen)
        }

        binding.viewPager2.setPageTransformer(compositePageTransformer)

        binding.viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                val fadeIn: Animation = AlphaAnimation(0f, 1f)
                fadeIn.interpolator = DecelerateInterpolator()
                fadeIn.repeatMode = 1
                fadeIn.duration = 700
                super.onPageSelected(position)
                when (position) {
                    1 -> {
                        binding.select1.setImageResource(R.drawable.ic_unselected)
                        binding.select2.setImageResource(R.drawable.ic_select)
                        binding.select2.animation = fadeIn
                        binding.title.text = "First to know"
                        binding.desc.text = getString(R.string.all_news_in_one_place_be_the_first_to_know_last_news)
                        binding.title.animation = fadeIn
                        binding.desc.animation = fadeIn
                        binding.select3.setImageResource(R.drawable.ic_unselected)
                    }
                    2 -> {
                        binding.select1.setImageResource(R.drawable.ic_unselected)
                        binding.select2.setImageResource(R.drawable.ic_unselected)
                        binding.select3.setImageResource(R.drawable.ic_select)
                        binding.select3.animation = fadeIn
                        binding.title.text = "Smart"
                        binding.desc.text = "Multifunctional app with support for 32 languages"
                        binding.title.animation = fadeIn
                        binding.desc.animation = fadeIn
                    }
                    else -> {
                        binding.select1.setImageResource(R.drawable.ic_select)
                        binding.select1.animation = fadeIn
                        binding.title.text = getString(R.string.res)
                        binding.desc.text = "Fast news only here, already new news blinked."
                        binding.title.animation = fadeIn
                        binding.desc.animation = fadeIn
                        binding.select2.setImageResource(R.drawable.ic_unselected)
                        binding.select3.setImageResource(R.drawable.ic_unselected)
                    }
                }
            }
        })
    }
}