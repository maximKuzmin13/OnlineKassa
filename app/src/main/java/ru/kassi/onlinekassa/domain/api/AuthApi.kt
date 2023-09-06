package ru.kassi.onlinekassa.domain.api

import retrofit2.http.POST
import retrofit2.http.Query

interface AuthApi {

    @POST("api/ApiTrying")
    suspend fun register(
        @Query("rq") rq: String?,
    ): Any

    @POST("api/ApiTrying")
    suspend fun auth(
        @Query("rq") rq: String?
    ): AuthResponse
}