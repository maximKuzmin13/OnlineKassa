package ru.kassi.onlinekassa.domain.api.auth

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class AuthRq(
    val login: String,
    val inn: String,
    val password: String
): Parcelable