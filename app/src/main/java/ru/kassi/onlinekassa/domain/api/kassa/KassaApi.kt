package ru.kassi.onlinekassa.domain.api.kassa

import retrofit2.http.POST
import retrofit2.http.Query

interface KassaApi {

    @POST("api/ApiTrying")
    suspend fun getKassaInfo(
        @Query("rq") rq: String?
    ): KassaResponse
}