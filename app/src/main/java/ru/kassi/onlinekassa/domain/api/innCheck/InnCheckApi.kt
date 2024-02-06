package ru.kassi.onlinekassa.domain.api.innCheck

import retrofit2.http.POST
import retrofit2.http.Query

interface InnCheckApi {

    @POST("api/ApiTrying")
    suspend fun innCheck(
        @Query("rq") rq: String?
    ): InnResponse
}