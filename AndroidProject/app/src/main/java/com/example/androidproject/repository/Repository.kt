package com.example.androidproject.repository

import com.example.androidproject.api.RetrofitInstance
import com.example.androidproject.data.retrofit.AllRestaurantsResponseData
import retrofit2.Response

class Repository {

    suspend fun getAllRestaurants(): Response<AllRestaurantsResponseData> {
        return RetrofitInstance.api.getAllRestaurants()
    }

    suspend fun getRestaurants(id: Int, city: String): Response<AllRestaurantsResponseData>{
        return RetrofitInstance.api.getRestaurants(id,city)
    }
}