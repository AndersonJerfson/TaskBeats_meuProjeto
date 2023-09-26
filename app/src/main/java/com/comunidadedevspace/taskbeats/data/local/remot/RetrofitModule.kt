package com.comunidadedevspace.taskbeats.data.local.remot

import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// https://api.thenewsapi.com/v1/news/top?api_token=${BuildConfig.API_KEY}&locale=us

object RetrofitModule {
    fun creatNewService(): NewsServise {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.thenewsapi.com/v1/news/")
            .addConverterFactory(GsonConverterFactory.create(Gson()))

        return retrofit.build().create(NewsServise::class.java)
    }
}