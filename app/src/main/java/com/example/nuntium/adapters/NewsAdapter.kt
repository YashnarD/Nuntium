package com.example.nuntium.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.nuntium.databinding.ItemUserBinding
import com.example.nuntium.models.Article
import com.squareup.picasso.Picasso

class UserAdapter(val listener: OnClick, val category: String) :
    androidx.recyclerview.widget.ListAdapter<Article, UserAdapter.Vh>(MyDiffUtill()) {

    inner class Vh(var itemUserBinding: ItemUserBinding) :
        RecyclerView.ViewHolder(itemUserBinding.root) {

        @SuppressLint("SetTextI18n")
        fun onBind(article: Article) {
            itemUserBinding.apply {
                title.text = category
                desc.text = article.title
                Picasso.get().load(article.urlToImage).into(image)
            }
            itemUserBinding.image.setOnClickListener {
                listener.onImageClick(article)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
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