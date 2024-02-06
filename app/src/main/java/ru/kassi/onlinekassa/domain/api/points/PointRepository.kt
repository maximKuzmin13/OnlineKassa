package ru.kassi.onlinekassa.domain.api.points

import android.content.SharedPreferences
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.kassi.onlinekassa.di.UserDataQualifier
import ru.kassi.onlinekassa.domain.api.Headers
import ru.kassi.onlinekassa.domain.api.Request
import javax.inject.Inject

class PointRepository @Inject constructor(
    @UserDataQualifier private val prefs: SharedPreferences,
    private val pointApi: PointApi,
    moshi: Moshi
) {
    private val jsonAdapter: JsonAdapter<Any> =
        moshi.adapter(Any::class.java)

    suspend fun getPointsList() = withContext(Dispatchers.IO) {
        pointApi.getPointsList(
            jsonAdapter.toJson(
                PointDto(
                    modul = "main",
                    action = "salespointsListGet",
                    dbs = "app",
                    user = "mobile",
                    tnx = prefs.getString("tnx", null),
                    rq = PointRq(
                        inn = prefs.getString("inn", null).orEmpty()
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