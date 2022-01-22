package com.example.nuntium.viewmodels

import com.example.nuntium.models.Headlines

sealed class NewsResource {

    object Loading: NewsResource()

    data class Success(val headlines: Headlines): NewsResource()

    data class Error(val message: String): NewsResource()
}