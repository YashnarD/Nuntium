package com.example.nuntium.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.nuntium.databinding.ItemNewBinding
import com.example.nuntium.databinding.ItemUserBinding
import com.example.nuntium.models.Article
import com.squareup.picasso.Picasso

class RecommendedAdapter(val listener: RecommendedAdapter.OnClick, val category: String) :
    ListAdapter<Article, RecommendedAdapter.Vh>(RecommendedAdapter.MyDiffUtill()) {

    inner class Vh(var itemNewBinding: ItemNewBinding) :
        RecyclerView.ViewHolder(itemNewBinding.root) {

        @SuppressLint("SetTextI18n")
        fun onBind(article: Article) {
            itemNewBinding.apply {
                titleNew.text = category
                desc.text = article.title
                Picasso.get().load(article.urlToImage).into(imageNew)
            }
            itemNewBinding.imageNew.setOnClickListener {
                listener.onImageClick(article)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendedAdapter.Vh {
        return Vh(ItemNewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecommendedAdapter.Vh, position: Int) {
        holder.onBind(getItem(position))
    }

    class MyDiffUtill : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }

    }

    interface OnClick {
        fun onImageClick(article: Article)
    }
}