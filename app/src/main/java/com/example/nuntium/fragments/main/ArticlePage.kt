package com.example.nuntium.fragments.main

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.example.nuntium.R
import com.example.nuntium.databinding.FragmentArticlePageBinding
import com.example.nuntium.models.Article
import com.squareup.picasso.Picasso
import android.content.Intent




private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ArticlePage : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    lateinit var article: Article

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
            article = arguments?.getSerializable("article") as Article
        }
    }

    lateinit var binding: FragmentArticlePageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentArticlePageBinding.inflate(inflater, container, false)
        activity?.window?.statusBarColor = ContextCompat.getColor(requireContext(), R.color.white)
        activity?.window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.newsDataTv.text = article.content
        binding.titleTv.text = article.title
        Picasso.get().load(article.urlToImage).into(binding.image)
        Picasso.get().load(article.urlToImage).into(binding.imageOnBg)
        binding.backImg.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.shareImg.setOnClickListener {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, article.description)
            shareIntent.putExtra(Intent.EXTRA_TITLE, article.title)
            startActivity(Intent.createChooser(shareIntent, "Share"))
        }
    }
}