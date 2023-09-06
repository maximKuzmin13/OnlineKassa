package ru.kassi.onlinekassa.domain.api.kassa

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class KassaData(
    val name: String,
    val address: String,
    val service: String,
    val term: String
): Parcelable