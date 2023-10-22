package com.example.newstry2.Api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NewsApi {
    private val retrofit = Retrofit.Builder()
    .baseUrl("https://newsapi.org/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

    val newsService:NewsService = retrofit.create(NewsService::class.java)

}