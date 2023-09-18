package com.comunidadedevspace.taskbeats.data.local.remot

data class NewsResponse(
    val count: Int,
    val items: List<NewsDto>
)
data class NewsDto(
    val id:Int,
    val imagens: String,
    val titulo: String
)

