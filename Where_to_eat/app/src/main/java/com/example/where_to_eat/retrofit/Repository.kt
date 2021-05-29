package com.example.where_to_eat.retrofit

import com.example.where_to_eat.retrofit.DataModel
import com.example.where_to_eat.retrofit.RetrofitInstance
import retrofit2.Response

class Repository {

    //Post

    suspend fun getPost(): Response<DataModel> {
        return RetrofitInstance.api.getPost()
    }
}