package com.example.nuntium.networking

import com.example.nuntium.models.Headlines
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("top-headlines")
    suspend fun getEntertainmentData(
        @Query("country") country: String = "us",
        @Query("category") category: String,
        @Query("apiKey") apiKey: String = "ec53550bda604e4dbb8b95a8027b5e3a"
    ): Response<Headlines>
}