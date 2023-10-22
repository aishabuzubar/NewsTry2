package com.example.newstry2

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newstry2.Api.NewsApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class NewsState{
    object Loading:NewsState()
    data class Success(val newsData:SourceResponse):NewsState()
    data class Error(val message: String): NewsState()
}

class NewsViewModel : ViewModel() {
    private val _newState = MutableStateFlow<NewsState>(NewsState.Loading)
    val newsState: StateFlow<NewsState> = _newState

    fun fetchNewsData(city:String){
        viewModelScope.launch {
            try {
                val  response = NewsApi.newsService.getnews(
                    apiKey = "73524ea38bee42aba144f39a97e1d661",
                    countryCode = city
                )
                _newState.value = NewsState.Success(response)
            } catch (e: Exception){
                _newState.value = NewsState.Error("Failed to Fetch")
            }
        }
    }

}
