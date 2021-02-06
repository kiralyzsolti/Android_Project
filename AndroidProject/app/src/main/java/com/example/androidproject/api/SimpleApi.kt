package com.example.androidproject.api

import com.example.androidproject.data.retrofit.AllRestaurantsResponseData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SimpleApi {
    @GET("/restaurants")
    suspend fun getAllRestaurants(): Response<AllRestaurantsResponseData>

    @GET("/restaurants")
    suspend fun getRestaurants(
            @Query("id") id:Int,
            @Query("city") city:String
    ): Response<AllRestaurantsResponseData>
}