package ru.kassi.onlinekassa.network

import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody
import org.json.JSONException
import org.json.JSONObject
import ru.kassi.onlinekassa.extentions.cloneBody
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class ExceptionsInterceptor @Inject constructor() : Interceptor {

    companion object {
        private const val ERROR_MESSAGE_FIELD = "errdesc"
    }

    override fun intercept(chain: Interceptor.Chain): Response = chain
        .proceed(chain.request())
        .also {
            getError(it, it.body)?.let { exception ->
                throw exception
            }
        }

    @Suppress("detekt.NestedBlockDepth")
    private fun getError(response: Response, body: ResponseBody?): IOException? = body
        ?.cloneBody()
        ?.let { responseBody ->
            try {
                val jsonObject = JSONObject(responseBody)
                val message = jsonObject.optString(ERROR_MESSAGE_FIELD)
                when {
                    responseBody.contains("err.sys") -> null
                    responseBody.contains("\"status\":\"errdesc\"") -> ServerException(
                        message = message
                    )

                    else -> null
                }
            } catch (error: JSONException) {
                when {
                    responseBody.contains("err.sys") -> null
                    responseBody.contains("\"status\":\"errdesc\"") -> ServerException()

                    else -> null
                }
            }
        }
}
