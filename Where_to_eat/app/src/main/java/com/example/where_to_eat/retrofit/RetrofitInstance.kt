package com.example.where_to_eat.retrofit

import com.example.where_to_eat.retrofit.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val retrofit by lazy{
        Retrofit.Builder()
            .baseUrl("https://run.mocky.io/v3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val api: MyApiCall by lazy {
        retrofit.create(MyApiCall::class.java)
    }
}