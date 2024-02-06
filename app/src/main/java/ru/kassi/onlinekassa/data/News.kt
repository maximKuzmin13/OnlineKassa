package ru.kassi.onlinekassa.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class News(
    val title: String,
    val desc: String,
    val link: String,
    val imageLink: String
)
