package com.comunidadedevspace.taskbeats.data.local.remot

import retrofit2.http.GET

interface NewsServise {

    @GET("api/v3/noticias/?tipo=noticia")
    suspend fun fetchNews(): NewsResponse

}