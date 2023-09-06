package ru.kassi.onlinekassa.presentation.authFragment

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import ru.kassi.onlinekassa.presentation.base.mvi.MviNavArgs

@JsonClass(generateAdapter = true)
@Parcelize
data class AuthNavArgs(
    val inn: String
): Parcelable, MviNavArgs
