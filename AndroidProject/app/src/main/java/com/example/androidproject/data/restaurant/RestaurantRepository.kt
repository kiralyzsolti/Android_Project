package com.example.androidproject.data.restaurant

import androidx.lifecycle.LiveData

class RestaurantRepository(private val restaurantDao: RestaurantDao) {
    val readAllData: LiveData<List<Restaurant>> = restaurantDao.readAllData()

    suspend fun addRestaurant(restaurant: Restaurant){
        restaurantDao.addRestaurant(restaurant)
    }
}