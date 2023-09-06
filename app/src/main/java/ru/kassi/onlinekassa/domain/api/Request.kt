package ru.kassi.onlinekassa.domain.api

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Request(
    val headers: Headers,
    val form: String?,
    val cookies: String?,
    val connection: String?
): Parcelable
