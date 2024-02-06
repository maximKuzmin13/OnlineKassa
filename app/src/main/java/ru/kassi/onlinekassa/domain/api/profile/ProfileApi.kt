package ru.kassi.onlinekassa.domain.api.profile

import retrofit2.http.POST
import retrofit2.http.Query

interface ProfileApi {

    @POST("api/ApiTrying")
    suspend fun getProfile(
        @Query("rq") rq: String?
    ): ProfileResponse?
}