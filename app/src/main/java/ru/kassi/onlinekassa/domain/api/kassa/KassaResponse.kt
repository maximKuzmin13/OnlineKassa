package ru.kassi.onlinekassa.domain.api.kassa

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class KassaResponse(
    val status: String,
    val response: Kassa
): Parcelable