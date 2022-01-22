package com.example.nuntium.repository

import com.example.nuntium.networking.ApiService
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NewsRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun getNews(category: String) =
        flow { emit(apiService.getEntertainmentData(category = category)) }
}