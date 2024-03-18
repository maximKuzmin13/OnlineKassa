package ru.kassi.onlinekassa.presentation.pinFragment

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class PinNavArgs(
    val firstAuth: Boolean
): Parcelable