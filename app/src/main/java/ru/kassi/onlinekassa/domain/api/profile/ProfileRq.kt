package ru.kassi.onlinekassa.domain.api.profile

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class ProfileRq(
    val empty: String? = null
): Parcelable