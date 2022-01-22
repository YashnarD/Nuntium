package com.example.nuntium.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nuntium.repository.NewsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsViewModel @Inject constructor(private val newsRepository: NewsRepository) : ViewModel() {

    fun getNews(category: String): StateFlow<NewsResource> {
        val stateFlow = MutableStateFlow<NewsResource>(NewsResource.Loading)
        viewModelScope.launch {
            val flow = newsRepository.getNews(category)
            flow.catch {
                stateFlow.emit(NewsResource.Error(it.message ?: ""))
            }.collect {
                if (it.isSuccessful) {
                    stateFlow.emit(NewsResource.Success(it.body()!!))
                } else {

                }
            }
        }
        return stateFlow
    }
}