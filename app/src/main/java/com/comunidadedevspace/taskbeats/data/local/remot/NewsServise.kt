package com.comunidadedevspace.taskbeats.data.local.remot

import com.comunidadedevspace.taskbeats.BuildConfig
import retrofit2.http.GET

interface NewsServise {
    // https://api.thenewsapi.com/v1/news/top?api_token=${BuildConfig.API_KEY}&locale=us

    @GET("top?api_token=${BuildConfig.API_KEY}&locale=pt")
    suspend fun fetchTopNews(): NewsResponse

    @GET("all?api_token=${BuildConfig.API_KEY}&locale=pt")
    suspend fun fetchAllNews(): NewsResponse

}