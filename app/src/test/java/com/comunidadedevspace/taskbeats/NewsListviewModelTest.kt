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
            val newsListTop = listOf(
                NewsDto(id = "ugugug",
                imagens = "img",
                titulo = "titulo"
             )
            )
            val newsListAll = listOf(
                NewsDto(id = "ugugug",
                    imagens = "img",
                    titulo = "titulo"
                )
            )
            val responseTop = NewsResponse(items = newsListAll)
            val responseAll = NewsResponse(items = newsListTop)


            whenever(service.fetchTopNews()).thenReturn(responseTop)
            whenever(service.fetchAllNews()).thenReturn(responseAll)

            undertest = NewsListViewModel(service)

            val result = undertest.newslivedata.getOrAwaitValue()

            assert(result == newsListTop + newsListAll)
        }

    }
}