package ru.kassi.onlinekassa.domain.api.points

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Point(
    val num: Int,
    val name: String,
    val adress: String,
    val registerName: String
): Parcelable