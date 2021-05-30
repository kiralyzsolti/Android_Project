package com.example.where_to_eat.data.restaurant

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "restaurants")
data class Restaurant (
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val address: String,
    val area: String,
    val city: String,
    val country: String,
    val image_url: String,
    val lat: Double,
    val lng: Double,
    val mobile_reserve_url: String,
    val name: String,
    val phone: String,
    val postal_code: String,
    val price: Double,
    val reserve_url: String,
    val state: String,
    var favourite: Int = 0
)
