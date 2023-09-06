package ru.kassi.onlinekassa.domain.api.auth

import retrofit2.http.POST
import retrofit2.http.Query

interface AuthApi {

    @POST("api/ApiTrying")
    suspend fun register(
        @Query("rq") rq: String?,
    ): AuthResponse

    @POST("api/ApiTrying")
    suspend fun auth(
        @Query("rq") rq: String?
    ): AuthResponse
}