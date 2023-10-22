package com.example.newstry2.Api

import com.example.newstry2.SourceResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("v2/top-headlines")
    suspend fun getnews(
        @Query("apiKey")
        apiKey: String ,
        @Query("country")
        countryCode: String

    ): SourceResponse
}