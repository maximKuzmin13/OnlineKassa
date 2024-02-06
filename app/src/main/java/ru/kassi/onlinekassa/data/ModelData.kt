package ru.kassi.onlinekassa.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ModelData(
    val model: String,
    val type: Boolean?
): Parcelable