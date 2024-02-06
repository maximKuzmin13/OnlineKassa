package ru.kassi.onlinekassa.domain.api.profile

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Profile(
    val inn: String,
    val fio: String,
    val mail: String
): Parcelable