package com.example.where_to_eat.retrofit

import retrofit2.Response

class Repository {

    suspend fun getAll(): Response<List<DataModel>>{
        return RetrofitInstance.api.getAll()
    }

    //test
    suspend fun getPost(): Response<DataModel> {
        return RetrofitInstance.api.getPost()
    }
}