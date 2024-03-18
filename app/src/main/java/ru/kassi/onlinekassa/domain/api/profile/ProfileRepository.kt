package ru.kassi.onlinekassa.domain.api.profile

import android.content.SharedPreferences
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.kassi.onlinekassa.di.UserDataQualifier
import ru.kassi.onlinekassa.domain.api.Headers
import ru.kassi.onlinekassa.domain.api.Request
import javax.inject.Inject

class ProfileRepository @Inject constructor(
    @UserDataQualifier private val prefs: SharedPreferences,
    private val profileApi: ProfileApi,
    moshi: Moshi
) {
    private val jsonAdapter: JsonAdapter<Any> =
        moshi.adapter(Any::class.java)

    suspend fun getProfile() = withContext(Dispatchers.IO) {
        profileApi.getProfile(
            jsonAdapter.toJson(
                ProfileDto(
                    modul = "main",
                    action = "profileGet",
                    dbs = "app",
                    user = "mobile",
                    tnx = prefs.getString("tnx", null),
                    rq = ProfileRq(
                        empty = null
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

    fun logout() {
        prefs.edit().clear().apply()
    }
}