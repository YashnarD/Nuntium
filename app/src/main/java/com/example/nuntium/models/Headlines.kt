package com.example.nuntium.models

import com.example.nuntium.models.Article
import java.io.Serializable

data class Headlines(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
) : Serializable