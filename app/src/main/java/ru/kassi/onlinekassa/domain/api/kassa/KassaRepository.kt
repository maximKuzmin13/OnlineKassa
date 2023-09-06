package ru.kassi.onlinekassa.domain.api.kassa

import android.content.SharedPreferences
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.kassi.onlinekassa.di.UserDataQualifier
import ru.kassi.onlinekassa.domain.api.Headers
import ru.kassi.onlinekassa.domain.api.Request
import javax.inject.Inject

class KassaRepository @Inject constructor(
    @UserDataQualifier private val prefs: SharedPreferences,
    private val kassaApi: KassaApi,
    moshi: Moshi
) {
    private val jsonAdapter: JsonAdapter<Any> =
        moshi.adapter(Any::class.java)

    suspend fun getKassaInfo(num: String) = withContext(Dispatchers.IO) {
        kassaApi.getKassaInfo(
            jsonAdapter.toJson(
                KassaDto(
                    modul = "main",
                    action = "salespointsListGet",
                    dbs = "app",
                    user = "mobile",
                    tnx = prefs.getString("tnx", null),
                    rq = KassaRq(
                        num = num
                    ),
                    request = Request(
                        headers = Headers(
                            tnx = null
                        ),
                        form = null,
                        cookies = null,
                        connection = null
                    )
                )
            )
        )
    }
}