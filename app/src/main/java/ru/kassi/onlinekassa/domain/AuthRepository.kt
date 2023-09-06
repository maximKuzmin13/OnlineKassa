package ru.kassi.onlinekassa.domain

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.kassi.onlinekassa.domain.api.AuthDto
import ru.kassi.onlinekassa.domain.api.AuthRq
import ru.kassi.onlinekassa.domain.api.Headers
import ru.kassi.onlinekassa.domain.api.Request
import ru.kassi.onlinekassa.domain.api.AuthApi
import ru.kassi.onlinekassa.domain.api.TestDto
import ru.kassi.onlinekassa.domain.api.TestRq
import ru.kassi.onlinekassa.network.ServerException
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val authApi: AuthApi,
    moshi: Moshi
) {

    var tnx: String? = null

    private val jsonAdapter: JsonAdapter<Any> =
        moshi.adapter(Any::class.java)

    suspend fun register() {
        authApi.register(
            jsonAdapter.toJson(
                TestDto(
                    modul = "main",
                    action = "salespointsListGet",
                    dbs = "app",
                    user = "mobile",
                    tnx = tnx,
                    rq = TestRq(
                        inn = "331000004893"
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

    suspend fun auth(login: String, inn: String, pass: String) = withContext(Dispatchers.IO) {
        val response = authApi.auth(
            jsonAdapter.toJson(
                AuthDto(
                    modul = "account",
                    action = "login",
                    dbs = "users",
                    user = "mobile",
                    rq = AuthRq(
                        login = login,
                        inn = inn,
                        password = pass
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
        tnx = response.response.tnx
    }
}