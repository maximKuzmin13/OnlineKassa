package ru.kassi.onlinekassa.domain.api.innCheck

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class InnRq(
    val inn: String
): Parcelable