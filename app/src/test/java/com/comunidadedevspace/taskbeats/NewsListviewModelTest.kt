package com.comunidadedevspace.taskbeats

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.comunidadedevspace.taskbeats.data.local.remot.NewsDto
import com.comunidadedevspace.taskbeats.data.local.remot.NewsResponse
import com.comunidadedevspace.taskbeats.data.local.remot.NewsServise
import com.comunidadedevspace.taskbeats.presentation.NewsListViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever


@OptIn(ExperimentalCoroutinesApi::class)
class NewsListviewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val service: NewsServise = mock()

    private lateinit var undertest: NewsListViewModel


    @Test
    fun `GIVEM request succeed news WHEN fetch THEM return list`(){
        runBlocking {
            val newsList = listOf(
                NewsDto(id = 1,
                imagens = "img",
                titulo = "titulo"
            )
            )
            val response = NewsResponse(count = 1, items = newsList)

            whenever(service.fetchTopNews()).thenReturn(response)

            undertest = NewsListViewModel(service)

            val result = undertest.newslivedata.getOrAwaitValue()

            assert(result == newsList)
        }

    }
}