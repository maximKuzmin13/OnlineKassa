package ru.kassi.onlinekassa.presentation.pdfFragment

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import ru.kassi.onlinekassa.presentation.base.mvi.MviNavArgs

@JsonClass(generateAdapter = true)
@Parcelize
data class PdfNavArgs(
    val model: String,
    val type: Boolean?,
    val num: String
): Parcelable, MviNavArgs