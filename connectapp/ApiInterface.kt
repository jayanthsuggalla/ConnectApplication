package com.example.connectapp

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("products/search")
    suspend fun searchProducts(@Query("q") query: String): Response<ProductResponse>


}