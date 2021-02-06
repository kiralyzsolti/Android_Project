package com.example.androidproject.data.restaurant

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "restaurants")
data class Restaurant(
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
    val image_url: String,
    //var favourite: Boolean = false
)
