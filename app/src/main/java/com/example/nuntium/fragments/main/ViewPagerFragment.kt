package com.example.nuntium.fragments.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.nuntium.App
import com.example.nuntium.R
import com.example.nuntium.adapters.UserAdapter
import com.example.nuntium.databinding.FragmentHomeBinding
import com.example.nuntium.databinding.FragmentViewPagerBinding
import com.example.nuntium.models.Article
import com.example.nuntium.viewmodels.NewsResource
import com.example.nuntium.viewmodels.NewsViewModel
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

private const val ARG_PARAM1 = "param1"

class ViewPagerFragment : Fragment(R.layout.fragment_view_pager), CoroutineScope {
    private var param1: Int? = null
    private lateinit var userAdapter: UserAdapter
    @Inject
    lateinit var newsViewModel: NewsViewModel
    private val binding by viewBinding(FragmentViewPagerBinding::bind)
    private lateinit var category: String
    var progressBar: ProgressBar? = null
    private var isCreate = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getInt(ARG_PARAM1)
        }

        App.appComponent.inject(this)
        progressBar = requireActivity().findViewById(R.id.progressBar)

        getCategoryByParam()

        userAdapter = UserAdapter(object : UserAdapter.OnClick {
            override fun onImageClick(article: Article) {
                val bundle = Bundle()
                bundle.putSerializable("article", article)
                findNavController().navigate(R.id.action_homepage_to_articlePage, bundle)
            }
        }, category)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(isCreate){
            when(param1){
                0 -> {
                    getViewModelInformation("sport")
                }
                1 -> {
                    getViewModelInformation("politics")
                }
                2 -> {
                    getViewModelInformation("life")
                }
                3 -> {
                    getViewModelInformation("gaming")
                }
                4 -> {
                    getViewModelInformation("animal")
                }
                5 -> {
                    getViewModelInformation("nature")
                }
                6 -> {
                    getViewModelInformation("food")
                }
                7 -> {
                    getViewModelInformation("art")
                }
                8 -> {
                    getViewModelInformation("history")
                }
                9 -> {
                    getViewModelInformation("fashion")
                }
            }
            isCreate = false
        }
    }

    private fun getCategoryByParam(): String {
        when(param1){
            0 -> category = "SPORT"
            1 -> category = "POLITICS"
            2 -> category = "LIFE"
            3 -> category = "GAMING"
            4 -> category = "ANIMAL"
            5 -> category = "NATURE"
            6 -> category = "FOOD"
            7 -> category = "ART"
            8 -> category = "HISTORY"
            9 -> category = "FASHION"
        }
        return category
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: Int) =
            ViewPagerFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, param1)
                }
            }
    }

        fun getViewModelInformation(tabVisible: String) {
        launch {
            newsViewModel.getNews(tabVisible)
                .collect {
                    when (it) {
                        is NewsResource.Loading -> {
                            progressBar?.visibility = View.VISIBLE
                        }

                        is NewsResource.Error -> {
                            progressBar?.visibility = View.GONE
                        }

                        is NewsResource.Success -> {
                            userAdapter.submitList(it.headlines.articles)
                            binding.rv.adapter = userAdapter
                            progressBar?.visibility = View.GONE
                        }
                    }
                }
        }
    }

    override val coroutineContext: CoroutineContext
        get() = Job() + Dispatchers.Main
}