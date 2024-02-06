package ru.kassi.onlinekassa.domain.api.innCheck

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import ru.kassi.onlinekassa.domain.api.Request
import ru.kassi.onlinekassa.domain.api.auth.AuthRq

@JsonClass(generateAdapter = true)
@Parcelize
data class InnDto(
    val modul : String,
    val action : String,
    val dbs : String,
    val user : String,
    val tnx : String? = null,
    val uname : String? = null,
    val rq: InnRq,
    val request: Request
): Parcelable
