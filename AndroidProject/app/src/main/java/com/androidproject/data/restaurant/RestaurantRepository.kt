package com.androidproject.data.restaurant

import androidx.lifecycle.LiveData
import com.androidproject.data.user.User
import com.androidproject.data.user.UserDao

class RestaurantRepository(private val restaurantDao: RestaurantDao) {
    val readAllData: LiveData<List<Restaurant>> = restaurantDao.readAllData()

    suspend fun addRestaurant(restaurant: Restaurant){
        restaurantDao.addRestaurant(restaurant)
    }
}