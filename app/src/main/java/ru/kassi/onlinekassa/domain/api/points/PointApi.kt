package ru.kassi.onlinekassa.domain.api.points

import retrofit2.http.POST
import retrofit2.http.Query

interface PointApi {

    @POST("api/ApiTrying")
    suspend fun getPointsList(
        @Query("rq") rq: String?
    ): PointResponse
}