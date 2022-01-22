package com.example.nuntium.fragments.main

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.nuntium.App
import com.example.nuntium.R
import com.example.nuntium.adapters.RecommendedAdapter
import com.example.nuntium.adapters.UserAdapter
import com.example.nuntium.adapters.ViewPagerAdapter
import com.example.nuntium.databinding.FragmentHomeBinding
import com.example.nuntium.databinding.ItemTabBinding
import com.example.nuntium.di.component.AppComponent
import com.example.nuntium.models.Article
import com.example.nuntium.networking.ApiService
import com.example.nuntium.repository.NewsRepository
import com.example.nuntium.viewmodels.NewsResource
import com.example.nuntium.viewmodels.NewsViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HomeFragment : Fragment(R.layout.fragment_home), CoroutineScope {
    private var param1: String? = null
    private var param2: String? = null
    @Inject
    lateinit var newsViewModel: NewsViewModel

    lateinit var adapter: RecommendedAdapter
    private var list = listOf("sport", "politics", "life", "gaming", "animal", "nature", "food", "art", "history", "fashion")

    lateinit var viewPagerAdapter: ViewPagerAdapter
    private val binding by viewBinding(FragmentHomeBinding::bind)
    private var isCreate = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)

        adapter = RecommendedAdapter(object : RecommendedAdapter.OnClick {
            override fun onImageClick(article: Article) {
                val bundle = Bundle()
                bundle.putSerializable("article", article)
                findNavController().navigate(R.id.action_homepage_to_articlePage, bundle)
            }
        },"Technology")
        activity?.window?.statusBarColor = ContextCompat.getColor(requireContext(), R.color.white)
        activity?.window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewPagerAdapter = ViewPagerAdapter(this, list)
        if(isCreate){
            launch {
                newsViewModel.getNews("technology")
                    .collect {
                        when (it) {
                            is NewsResource.Loading -> {
                                binding.progressBar2.visibility = View.VISIBLE
                            }

                            is NewsResource.Error -> {
                                binding.progressBar2.visibility = View.GONE
                            }

                            is NewsResource.Success -> {
                                adapter.submitList(it.headlines.articles)
                                binding.progressBar2.visibility = View.GONE
                            }
                        }
                    }
            }

            isCreate = false
        }

        binding.recommendedRv.adapter = adapter
        binding.viewPager.adapter = viewPagerAdapter

        binding.viewPager.isUserInputEnabled = false
        binding.viewPager.isSaveEnabled = false

            TabLayoutMediator(
                binding.tabLayout,
                binding.viewPager,
                true,
                object : TabLayoutMediator.TabConfigurationStrategy {
                    override fun onConfigureTab(tab: TabLayout.Tab, position: Int) {
                        tab.setText(list[position])

                        val itemTabBinding = ItemTabBinding.inflate(layoutInflater)
                        itemTabBinding.tv.setText(tab.text)
                        if (position == 0) {
                            itemTabBinding.tv.setTextColor(Color.WHITE)
                            itemTabBinding.card.setCardBackgroundColor(Color.parseColor("#475AD7"))
                        } else {
                            itemTabBinding.tv.setTextColor(Color.parseColor("#7C82A1"))
                            itemTabBinding.card.setCardBackgroundColor(Color.parseColor("#F3F4F6"))
                        }
                        tab.setCustomView(itemTabBinding.root)
                    }

                }).attach()

            binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    val itemTabBinding = ItemTabBinding.bind(tab?.customView!!)
                    itemTabBinding.tv.setTextColor(Color.WHITE)
                    itemTabBinding.card.setCardBackgroundColor(Color.parseColor("#475AD7"))
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                    val itemTabBinding = ItemTabBinding.bind(tab?.customView!!)
                    itemTabBinding.tv.setTextColor(Color.parseColor("#7C82A1"))
                    itemTabBinding.card.setCardBackgroundColor(Color.parseColor("#F3F4F6"))
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                }

            })

            binding.seeAllTv.setOnClickListener {
                val bundle = Bundle()
                bundle.putString("key", "Technology")
                findNavController().navigate(R.id.seeAllFragment, bundle)
            }
    }

    override val coroutineContext: CoroutineContext
        get() = Job() + Dispatchers.Main

}