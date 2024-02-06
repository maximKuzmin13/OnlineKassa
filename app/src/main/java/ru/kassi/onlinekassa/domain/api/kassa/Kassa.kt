package ru.kassi.onlinekassa.domain.api.kassa

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Kassa(
    val name: String,
    val address: String,
    val KKTmodel: String,
    val CassPO: String,
    val CassPOdate: String,
    val CassPOobnovl: String,
    val CassPOobnovlDate: String,
    val OFDname: String,
    val OFDdate: String,
    val OFDpin: String,
    val FNtype: String,
    val FNmodel: String,
    val FNdate: String,
    val tovaroUch: String,
    val tovaroUchDate: String
): Parcelable