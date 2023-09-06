package ru.kassi.onlinekassa.domain.api.kassa

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Kassa(
    val name: String,
    val address: String,
    val OFD: String,
    val FN: String
): Parcelable