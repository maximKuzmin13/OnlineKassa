package ru.kassi.onlinekassa.presentation.webviewFragment

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import ru.kassi.onlinekassa.presentation.base.mvi.MviNavArgs

@Parcelize
data class WebviewNavArgs(
    val link: String
): Parcelable, MviNavArgs
