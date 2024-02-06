package ru.kassi.onlinekassa.domain.api.innCheck

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.kassi.onlinekassa.domain.api.Headers
import ru.kassi.onlinekassa.domain.api.Request
import javax.inject.Inject

class InnRepository @Inject constructor(
    private val innApi: InnCheckApi,
    moshi: Moshi
) {
    private val jsonAdapter: JsonAdapter<Any> =
        moshi.adapter(Any::class.java)

    suspend fun checkInn(inn: String) = withContext(Dispatchers.IO) {
        innApi.innCheck(
            jsonAdapter.toJson(
                InnDto(
                    modul = "account",
                    action = "check",
                    dbs = "users",
                    user = "mobile",
                    tnx = null,
                    rq = InnRq(
                        inn = inn
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