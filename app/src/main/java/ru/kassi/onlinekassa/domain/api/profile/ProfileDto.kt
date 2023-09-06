package ru.kassi.onlinekassa.domain.api.profile

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import ru.kassi.onlinekassa.domain.api.Request

@JsonClass(generateAdapter = true)
@Parcelize
data class ProfileDto(
    val modul : String,
    val action : String,
    val dbs : String,
    val user : String,
    val tnx : String? = null,
    val uname : String? = null,
    val rq: ProfileRq,
    val request: Request
): Parcelable
