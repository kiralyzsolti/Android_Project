package com.example.where_to_eat.retrofit

import retrofit2.Response
import retrofit2.http.GET

interface MyApiCall {

    // https://run.mocky.io/v3/       9bfd65a8-7433-4f51-b0c8-d7322a91eb79

    //@GET("9bfd65a8-7433-4f51-b0c8-d7322a91eb79")
    @GET("636a30d1-7d1f-4394-bed1-449c9ef4107a")
    suspend fun getPost(): Response<DataModel>


}