package ru.kassi.onlinekassa.presentation.kassiFragment

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import ru.kassi.onlinekassa.presentation.base.mvi.MviNavArgs

@JsonClass(generateAdapter = true)
@Parcelize
data class KassaNavArgs(
    val num: String
): Parcelable, MviNavArgs