package com.example.sunstone.api

import com.example.sunstone.models.ResponseModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiClient {
    @GET("curated")
    fun getWallpaper(
        @Header("Authorization") credentials: String?,
        @Query("page") pageCount: Int,
        @Query("per_page") perPage: Int
    ): Call<ResponseModel?>?
}