package com.example.nuntium.fragments.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.nuntium.App
import com.example.nuntium.R
import com.example.nuntium.adapters.RecommendedAdapter
import com.example.nuntium.databinding.FragmentSeeAllBinding
import com.example.nuntium.models.Article
import com.example.nuntium.viewmodels.NewsResource
import com.example.nuntium.viewmodels.NewsViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

private const val ARG_PARAM1 = "key"

class SeeAllFragment : Fragment(), CoroutineScope {
    private var param1: String? = null
    private lateinit var binding: FragmentSeeAllBinding
    private lateinit var adapter: RecommendedAdapter
    @Inject
    lateinit var newsViewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSeeAllBinding.inflate(inflater, container, false)
        App.appComponent.inject(this)

        adapter = RecommendedAdapter(object : RecommendedAdapter.OnClick {
            override fun onImageClick(article: Article) {
                val bundle = Bundle()
                bundle.putSerializable("article", article)
                findNavController().navigate(R.id.action_seeAllFragment_to_articlePage, bundle)
            }
        },"Technology")

        launch {
            newsViewModel.getNews("technology")
                .collect {
                    when (it) {
                        is NewsResource.Loading -> {
                            binding.progress.visibility = View.VISIBLE
                        }

                        is NewsResource.Error -> {
                            binding.progress.visibility = View.GONE
                        }

                        is NewsResource.Success -> {
                            adapter.submitList(it.headlines.articles.shuffled())
                            binding.progress.visibility = View.GONE
                        }
                    }
                }
        }

        binding.recommendedRv.adapter = adapter

        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }

        return binding.root
    }

    override val coroutineContext: CoroutineContext
        get() = Job() + Dispatchers.Main
}