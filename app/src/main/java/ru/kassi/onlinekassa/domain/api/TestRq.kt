package ru.kassi.onlinekassa.domain.api

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class TestRq(
    val inn: String
): Parcelable
