package ru.kassi.onlinekassa.domain.api.auth

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import ru.kassi.onlinekassa.domain.api.TokenResponse

@JsonClass(generateAdapter = true)
@Parcelize
data class AuthResponse(
    val status: String,
    val response: TokenResponse?
): Parcelable