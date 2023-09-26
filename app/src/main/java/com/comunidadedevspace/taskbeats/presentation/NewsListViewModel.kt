package com.comunidadedevspace.taskbeats.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.comunidadedevspace.taskbeats.data.local.remot.NewsDto
import com.comunidadedevspace.taskbeats.data.local.remot.NewsServise
import com.comunidadedevspace.taskbeats.data.local.remot.RetrofitModule
import kotlinx.coroutines.launch
import java.lang.Exception

class NewsListViewModel(
    val newsservice: NewsServise
): ViewModel() {
    init {
        getNews()
    }

    private val _newsLiveData = MutableLiveData<List<NewsDto>>()
    val newslivedata: LiveData<List<NewsDto>> = _newsLiveData

    private fun getNews(){
        viewModelScope.launch {
            try {
                val respondTop = newsservice.fetchTopNews().items
                val respodAll = newsservice.fetchAllNews().items
                _newsLiveData.value = respondTop + respodAll



            }catch (ex: Exception){
                ex.printStackTrace()
            }


        }
    }

  companion object{

      fun creat(): NewsListViewModel {
          val newsService = RetrofitModule.creatNewService()
          return NewsListViewModel(newsService)

      }
  }
}