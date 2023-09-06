package ru.kassi.onlinekassa.domain.api.auth

import android.content.SharedPreferences
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.kassi.onlinekassa.di.UserDataQualifier
import ru.kassi.onlinekassa.domain.api.Headers
import ru.kassi.onlinekassa.domain.api.Request
import ru.kassi.onlinekassa.domain.api.TestDto
import ru.kassi.onlinekassa.domain.api.TestRq
import javax.inject.Inject

class AuthRepository @Inject constructor(
    @UserDataQualifier private val prefs: SharedPreferences,
    private val authApi: AuthApi,
    moshi: Moshi
) {

    private val jsonAdapter: JsonAdapter<Any> =
        moshi.adapter(Any::class.java)

    suspend fun register(login: String, inn: String, pass: String) {
        val response = authApi.register(
            jsonAdapter.toJson(
                AuthDto(
                    modul = "account",
                    action = "register",
                    dbs = "users",
                    user = "mobile",
                    tnx = null,
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
        prefs.edit().putString("inn", inn).apply()
        prefs.edit().putString("tnx", response.response.tnx).apply()
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
        prefs.edit().putString("inn", inn).apply()
        prefs.edit().putString("tnx", response.response.tnx).apply()
    }
}