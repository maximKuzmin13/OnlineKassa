package ru.kassi.onlinekassa.domain.api.auth

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import ru.kassi.onlinekassa.domain.api.Request

@JsonClass(generateAdapter = true)
@Parcelize
data class AuthDto(
    val modul : String,
    val action : String,
    val dbs : String,
    val user : String,
    val tnx : String? = null,
    val uname : String? = null,
    val rq: AuthRq,
    val request: Request
): Parcelable