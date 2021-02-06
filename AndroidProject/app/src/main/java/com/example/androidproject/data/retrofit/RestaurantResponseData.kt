package com.example.androidproject.data.retrofit

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class RestaurantResponseData (
    //@SerializedName("id")
    @PrimaryKey(autoGenerate = true)

    val id: Int,
    val name: String,
    val address: String,
    val city: String,
    val state: String,
    val area: String,
    val postal_code: String,
    val country: String,
    val phone: String,
    val lat: Float,
    val lng: Float,
    val price: Int,
    val reserve_url: String,
    val mobile_reserve_url: String,
    val image_url: String
    )