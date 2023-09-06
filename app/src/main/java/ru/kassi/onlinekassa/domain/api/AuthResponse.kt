package ru.kassi.onlinekassa.domain.api

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class AuthResponse(
    val status: String,
    val response: TokenResponse
): Parcelable