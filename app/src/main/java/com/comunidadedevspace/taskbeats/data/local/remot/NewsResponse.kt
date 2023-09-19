package com.comunidadedevspace.taskbeats.data.local.remot

import com.google.gson.annotations.SerializedName

data class NewsResponse(
    @SerializedName("data")
    val items: List<NewsDto>
)
data class NewsDto(
    @SerializedName("uuid")
    val id:String,
    @SerializedName("image_url")
    val imagens: String,
    @SerializedName("snippet")
    val titulo: String
)

